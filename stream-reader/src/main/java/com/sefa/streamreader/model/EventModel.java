package com.sefa.streamreader.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String timestamp;
}
