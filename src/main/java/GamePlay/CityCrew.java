package GamePlay;

import Grammar.Plan.Direction;

interface CityCrewI {
    int getCurcol();
    int getCurrow();
    double getDeposit();
    double getInterest();
    double getMaxDeposit();
    int opponent(Territory t);
    int nearby(Territory t, Direction direction);
    void relocate();
    void move(Direction direction);
    void invest(int amount);

    double collect(int amount);

    void shoot(Direction direction, int amount);
}

public class CityCrew implements CityCrewI {
    private int currow;
    private int curcol;

    public CityCrew(int currow, int curcol) {
        this.currow = currow;
        this.curcol = curcol;
    }

    @Override
    public int getCurcol() {
        return curcol;
    }

    @Override
    public int getCurrow() {
        return currow;
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
    public int nearby(Territory t, Direction direction) {
        return 0;
    }

    @Override
    public void relocate() {

    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void invest(int amount) {

    }

    @Override
    public double collect(int amount) {
        return 0;
    }

    @Override
    public void shoot(Direction direction, int amount) {

    }
}
