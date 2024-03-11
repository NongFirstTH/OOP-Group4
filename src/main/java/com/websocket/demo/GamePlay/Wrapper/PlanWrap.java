package com.websocket.demo.GamePlay.Wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlanWrap {
    private final int player;
    private final String plan;

    @JsonCreator
    public PlanWrap(@JsonProperty("player") int player,
                    @JsonProperty("plan") String plan) {
        this.player = player;
        this.plan = plan;
    }
}
