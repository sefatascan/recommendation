package com.sefa.streamreader.service;

import com.sefa.streamreader.entity.HistoryEntity;
import com.sefa.streamreader.model.EventModel;
import com.sefa.streamreader.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void convertEventAndPrepareHistory(EventModel eventModel) {
        historyRepository.save(prepareHistoryEntity(eventModel));
    }

    private HistoryEntity prepareHistoryEntity(EventModel eventModel) {
        return HistoryEntity.builder()
                            .userId(eventModel.getUserId())
                            .productId(eventModel.getProperties().getProductId())
                            .contextSource(eventModel.getContext().getSource())
                            .viewedAt(LocalDateTime.parse(eventModel.getTimestamp()))
                            .build();
    }
}
