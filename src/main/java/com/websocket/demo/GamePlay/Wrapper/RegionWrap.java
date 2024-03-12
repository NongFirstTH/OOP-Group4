package com.websocket.demo.GamePlay.Wrapper;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegionWrap {
    private final String player;
    private final long deposit;

    public RegionWrap(String player, long deposit) {
        this.player = player;
        this.deposit = deposit;
    }
}
