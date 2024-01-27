package GamePlay;

interface CityCrewI {
    public int getCurcol();
    public int getCurrow();
    public void move(String direction);
}

public class CityCrew implements CityCrewI {
    private int curcol;
    //return current column of crew
    private int currow;

    public CityCrew() {
    }

    @Override
    public int getCurcol() {
        return 0;
    }

    @Override
    public int getCurrow() {
        return 0;
    }

    @Override
    public void move(String direction) {

    }
}
