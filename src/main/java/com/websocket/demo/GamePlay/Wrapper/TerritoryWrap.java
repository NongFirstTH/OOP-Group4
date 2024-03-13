package com.websocket.demo.GamePlay.Wrapper;
import lombok.Getter;

@Getter
public class TerritoryWrap {
    private final RegionWrap[][] territory;
    private final int row;
    private final int col;
    private final int currow;
    private final int curcol;

    public TerritoryWrap(RegionWrap[][] territory, int row, int col, int currow, int curcol) {
        this.territory = territory;
        this.row = row;
        this.col = col;
        this.currow = currow;
        this.curcol = curcol;
    }
}
