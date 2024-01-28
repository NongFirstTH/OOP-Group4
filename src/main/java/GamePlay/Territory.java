package GamePlay;

interface TerritoryI {
    public Region getRegions(int row, int col);
}

public class Territory implements TerritoryI {
    private Region[][] regions;
    @Override
    public Region getRegions(int row, int col) {
        return null;
    }
}
