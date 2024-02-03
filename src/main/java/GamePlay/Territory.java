package GamePlay;

interface TerritoryI {
    Region getRegions(int row, int col);
    Player getOwner(int row, int col);
}

public class Territory implements TerritoryI {
    private Region[][] regions;

    public Territory(int row ,int col){
        regions = new Region[row][col];
        for (int i = 0;i<row;i++){
            for (int j = 0;j<col;j++){
                regions[i][j] = new Region();
            }
        }
    }
    @Override
    public Region getRegions(int row, int col) {
        return regions[row][col];
    }

    @Override
    public Player getOwner(int row, int col) {
        return null;
    }
}
