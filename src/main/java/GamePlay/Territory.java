package GamePlay;

interface TerritoryI {
    Region getRegions(int row, int col);
    Player getOwner(int row, int col);
}

public class Territory implements TerritoryI {
    private Region[][] regions;
    @Override
    public Region getRegions(int row, int col) {
        return null;
    }

    @Override
    public Player getOwner(int row, int col) {
        return null;
    }
}
