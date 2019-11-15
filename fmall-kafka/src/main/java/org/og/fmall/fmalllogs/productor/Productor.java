package org.og.fmall.fmalllogs.productor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.og.fmall.fmalllogs.config.KafkaProperties;
import org.og.fmall.fmalllogs.exception.NoTopicFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Productor {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaProperties kafkaProperties;
    public void sendMessage(String value,String topic){
        String disTopic = kafkaProperties.getTopic();
        if (topic != null){
            disTopic = topic;
        }
        if (disTopic == null){
            throw new NoTopicFoundException("如未指定topic，请在application中配置默认发送topic");
        }
        ProducerRecord<String,String> record = new ProducerRecord<>(disTopic,value);
        try {
            kafkaProducer.send(record);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendMessage(String value){
        this.sendMessage(value,null);
    }
}
