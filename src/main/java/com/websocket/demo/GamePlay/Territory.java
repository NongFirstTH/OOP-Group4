package com.websocket.demo.GamePlay;

import com.websocket.demo.GamePlay.Wrapper.RegionWrap;

interface TerritoryI {
    Region getRegion(int row, int col);
    Player getOwner(int row, int col);
    RegionWrap[][] wrap();
}

public class Territory implements TerritoryI {
    private final int row;//rows in game
    private final int col;//cols in game
    private final Region[][] regions;

    public Territory(int row ,int col){
        this.row = row;
        this.col = col;
        regions = new Region[row][col];
        for (int i = 0;i<row;i++){
            for (int j = 0;j<col;j++){
                regions[i][j] = new Region();
            }
        }
    }
    @Override
    public Region getRegion(int row, int col) {
        if(row < 1 || row > this.row || col < 1 || col > this.col) return null;
        return regions[row-1][col-1];
    }

    @Override
    public Player getOwner(int row, int col) {
        return regions[row-1][col-1].getOwner();
    }

    @Override
    public RegionWrap[][] wrap() {
        RegionWrap[][] regions = new RegionWrap[row][col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                regions[i][j] = getRegion(i+1, j+1).wrap();
            }
        }
        return regions;
    }
}
