package com.sefa.viewproducer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sefa.viewproducer.model.EventModel;
import com.sefa.viewproducer.producer.EventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonFileReader {

    private final ObjectMapper objectMapper;

    private final EventProducer eventProducer;

    public void read(String path){
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            log.info("File reading process started ...");
            String line = reader.readLine();
            while (Objects.nonNull(line)){
                EventModel event = objectMapper.readValue(line, EventModel.class);
                eventProducer.send(event);
                line = reader.readLine();
            }
            log.info("File reading process finished ...");

        } catch (FileNotFoundException e) {
            log.error("File not found at '{}'", path);
        } catch (IOException e) {
            log.error("Unexpected error.");
        }
    }
}
