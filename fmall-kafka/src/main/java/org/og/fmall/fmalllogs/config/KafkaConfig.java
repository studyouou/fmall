package org.og.fmall.fmalllogs.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@EnableConfigurationProperties(KafkaProperties.class)
@Configuration
@Slf4j
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaProducer kafkaProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",kafkaProperties.getBootstrapServer());
        properties.put("key.serializer",kafkaProperties.getKeySerializer());
        properties.put("value.serializer",kafkaProperties.getValueSerializer());
        if (kafkaProperties.getAcks() != null){
            properties.put("acks",kafkaProperties.getAcks());
        }
        if (kafkaProperties.getRetries() != 0){
            properties.put("retries",kafkaProperties.getRetries());
        }
        if (kafkaProperties.getBufferMmory() != 0L){
            properties.put("buffer.memory",kafkaProperties.getBufferMmory());
        }
        if (kafkaProperties.getBatchSize() != 0L){
            properties.put("batch.size",kafkaProperties.getBatchSize());
        }
        if (kafkaProperties.getLingerMs() != 0){
            properties.put("linger.ms",kafkaProperties.getLingerMs());
        }
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        log.info("已加载kafka");
        return kafkaProducer;
    }
}
