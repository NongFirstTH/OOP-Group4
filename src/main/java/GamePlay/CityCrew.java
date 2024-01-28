package GamePlay;

interface CityCrewI {
    int getCurcol();
    int getCurrow();
    double getDeposit();
    double getInterest();
    double getMaxDeposit();
    int opponent(Territory t);
    int nearby(Territory t, String direction);
    void relocate();
    void move(String direction);
    void invest(int amount);

    double collect(int amount);

    void shoot(String direction, int amount);
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
    public double getDeposit() {
        return 0;
    }

    @Override
    public double getInterest() {
        return 0;
    }

    @Override
    public double getMaxDeposit() {
        return 0;
    }

    @Override
    public int opponent(Territory t) {
        return 0;
    }

    @Override
    public int nearby(Territory t, String direction) {
        return 0;
    }

    @Override
    public void relocate() {

    }

    @Override
    public void move(String direction) {

    }

    @Override
    public void invest(int amount) {

    }

    @Override
    public double collect(int amount) {
        return 0;
    }

    @Override
    public void shoot(String direction, int amount) {

    }
}
