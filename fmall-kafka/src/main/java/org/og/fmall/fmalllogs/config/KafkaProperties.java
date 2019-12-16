package org.og.fmall.fmalllogs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String bootstrapServer;

    private String keySerializer;

    private String valueSerializer;

    private String topic;

    private String acks ;

    private int retries;

    private long bufferMmory;

    private int batchSize;

    private int lingerMs;

}
