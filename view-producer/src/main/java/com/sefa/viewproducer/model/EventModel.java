package com.sefa.viewproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {

    @JsonProperty("event")
    private String eventName;

    @JsonProperty("messageid")
    private String messageId;

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("properties")
    private PropertyModel properties;

    @JsonProperty("context")
    private ContextModel context;

    @JsonProperty("timestamp")
    private String timestamp = LocalDateTime.now().toString();
}
