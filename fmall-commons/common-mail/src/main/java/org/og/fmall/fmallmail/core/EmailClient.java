package org.og.fmall.fmallmail.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: OuGen
 * @create: 2019-12-11 14:20
 **/
@Service
public class EmailClient {

    @Autowired
    private MailSender mailSender;

    @Value("${mail.receiver:304489914@qq.com}")
    private String[] receiver;

    @Value("${spring.mail.username}")
    private String username;

    public void sendMail(String subject,String msg){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setText(msg);
        simpleMailMessage.setSubject(subject);
        mailSender.send(simpleMailMessage);
    }

}
