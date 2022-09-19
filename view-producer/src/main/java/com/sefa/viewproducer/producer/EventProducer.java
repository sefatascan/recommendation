package com.sefa.viewproducer.producer;

import com.sefa.viewproducer.model.EventModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducer {

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(EventModel event) {
        try {
            kafkaTemplate.send(topicName, event);
        } catch (Exception e) {
            log.debug("Failed to send kafka event with messageId : {} ", event.getMessageId());
        }
    }

}
