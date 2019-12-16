package org.og.fmall.fmallshop.config;

import ch.qos.logback.classic.net.SocketNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.og.fmall.fmallmail.core.EmailClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

/**
 * @description: 检查系统能否正常连接  连接出错发送邮件
 * @author: OuGen
 * @create: 2019-12-11 16:03
 **/
@ConditionalOnProperty(prefix = "soft",name = "needTest",havingValue = "true")
@Component
@Slf4j
public class SoftTest implements InitializingBean {

    @Value("${soft.time:600000}")
    private int time;

    @Autowired
    private EmailClient emailClient;

    private final Map<String,Boolean> hasSended = new HashMap<>(32) ;

    private final Map<String,InetSocketAddress> addressMap = new HashMap<>(32);

    private final Map<String,String> url = new HashMap<>(32);

    @Override
    public void afterPropertiesSet() throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("testSoft/soft.properties");
        if (resourceAsStream == null){
            throw new RuntimeException("未找到soft.properties文件");
        }
        BufferedReader reader =new BufferedReader(new InputStreamReader(resourceAsStream));
        String line = null;
        while ((line = reader.readLine())!= null){
            splitLine(line);
        }
        new Thread(() ->{
            List<String> downSystem = new ArrayList<>(url.size());
            StringBuilder builder = new StringBuilder();
            while (true){
                for (Map.Entry<String,String> entry : url.entrySet()) {
                    try {
                        Socket socket = new Socket();
                        InetSocketAddress address = addressMap.get(entry.getKey());
                        if (address == null){
                            String[] split = entry.getValue().split(":");
                            address = new InetSocketAddress(split[0],Integer.parseInt(split[1]));
                            addressMap.put(entry.getKey(),address);
                        }
                        socket.connect(address);
                        hasSended.put(entry.getKey(),false);
                        socket.close();
                        socket = null;
                    } catch (Exception e) {
                        log.error(entry.getKey()+"挂掉了");
                        downSystem.add(entry.getKey());
                    }
                }
                if (downSystem.size() > 0) {
                    downSystem.forEach((s)->{
                        if (!hasSended.get(s)) {
                            builder.append(s).append("挂掉，尽快重启").append("\r\n");
                            hasSended.put(s,true);
                        }
                    });
                    if (builder.length() > 0) {
                        emailClient.sendMail("系统挂掉", builder.toString());
                    }
                    builder.delete(0,builder.length());
                    downSystem.clear();
                }
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void splitLine(String line) throws IOException {
        String[] split = line.split("=");
        hasSended.put(split[0],false);
        url.put(split[0],split[1]);
    }

}
