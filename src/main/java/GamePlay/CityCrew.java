package GamePlay;

import Grammar.Plan.Direction;

interface CityCrewI {
    int getCurcol();
    int getCurrow();
    double getDeposit();
    long getInterest();
    long getMaxDeposit();
    long opponent(Territory t);
    long nearby(Territory t, Direction direction);
    void relocate();
  
    Player ownerMoveTo(Direction direction, Territory t);

    void move(Direction direction, Territory t);
  
    void invest(long amount);

    long collect(long amount);

    void shoot(Direction direction, long amount);
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
    public long getInterest() {
        return 0;
    }

    @Override
    public long getMaxDeposit() {
        return 0;
    }

    @Override
    public long opponent(Territory t) {
        return 0;
    }

    @Override
    public long nearby(Territory t, Direction direction) {
        return 0;
    }

    @Override
    public void relocate() {

    }

    @Override
    public Player ownerMoveTo(Direction direction, Territory t) {
        return null;
    }

    @Override
    public void move(Direction direction, Territory t) {

    }

    @Override
    public void invest(long amount) {

    }

    @Override
    public long collect(long amount) {
        return 0;
    }

    @Override
    public void shoot(Direction direction, long amount) {

    }
}
