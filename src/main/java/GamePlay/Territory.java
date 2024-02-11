package GamePlay;

interface TerritoryI {
    Region getRegions(int row, int col);
    Player getOwner(int row, int col);
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
    public Region getRegions(int row, int col) {
        if(row < 1 || row > this.row || col < 1 || col > this.col) return null;
        return regions[row-1][col-1];
    }

    @Override
    public Player getOwner(int row, int col) {
        return regions[row-1][col-1].getOwner();
    }
}
