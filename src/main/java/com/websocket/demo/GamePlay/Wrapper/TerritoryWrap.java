package com.websocket.demo.GamePlay.Wrapper;
import lombok.Getter;

@Getter
public class TerritoryWrap {
    private final RegionWrap[][] territory;
    private final PlayerWrap[] players;

    public TerritoryWrap(RegionWrap[][] territory, PlayerWrap[] players) {
        this.territory = territory;
        this.players = players;
    }
}
