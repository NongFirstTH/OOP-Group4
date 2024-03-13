package com.websocket.demo.GamePlay.Wrapper;

import lombok.Getter;

@Getter
public class PlayerWrap {
    private final String name;
    private final int row;
    private final int col;
    private final int currow;
    private final int curcol;

    public PlayerWrap(String name, int row, int col, int currow, int curcol) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.currow = currow;
        this.curcol = curcol;
    }
}
