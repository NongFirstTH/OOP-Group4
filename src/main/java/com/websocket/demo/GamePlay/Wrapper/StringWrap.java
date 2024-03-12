package com.websocket.demo.GamePlay.Wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StringWrap {
    private final String text;

    @JsonCreator
    public StringWrap(@JsonProperty("text") String text) {
        this.text = text;
    }
}