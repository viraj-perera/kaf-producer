package com.vipe.kafka.pub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Notification {

    @Setter
    @JsonProperty
    private Long id;

    @JsonProperty
    private String message;

    @JsonProperty
    private Type type;

    public enum Type{
        ALERT, REMINDER, UPDATE
    }

    public Notification(Long id, String message, Type type) {
        this.message = message;
        this.type = type;
    }
}
