package com.sefa.streamreader.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sefa.streamreader.model.EventModel;
import com.sefa.streamreader.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ObjectMapper mapper;

    private final HistoryService historyService;

    @KafkaListener(topics = "events", groupId = "view", containerFactory = "kafkaListenerContainerFactory")
    public void viewEventConsumer(String event) {
        try {
            EventModel eventModel = mapper.readValue(event, EventModel.class);
            historyService.convertEventAndPrepareHistory(eventModel);
        } catch (Exception e) {
            log.warn("Exception for consuming view events", e);
        }
    }
}
