package org.og.fmall.commontools.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * @author:ougen
 * @date:2019/9/2515:02
 */
@ConfigurationProperties("own.redis")
@Data
public class OwnRedisProperties {
    private String address; //连接地址

    private int port;
    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout;

    private String password;

    private int maxIdle; /**连接池中的最大空闲连接**/

    private int minIdle;  /**最小连接数**/

    private int maxActive;/**连接池最大连接数**/

    private long maxWait;/**连接池最大阻塞等待时间**/

}
