package com.websocket.demo.GamePlay.Wrapper;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionWrap {
    private final int row;
    private final int col;
    private final int player;
    private final long deposit;
}
