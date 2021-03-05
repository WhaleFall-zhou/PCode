package com.pcode.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PcodeProductor {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String topicName,String message){
        if(Strings.isNotBlank(topicName)&&Strings.isNotBlank(message)){
            kafkaTemplate.send(topicName,message);
        }
    }
}
