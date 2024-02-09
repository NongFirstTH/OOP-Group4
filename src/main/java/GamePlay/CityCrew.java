package GamePlay;

import Grammar.Plan.Direction;

interface CityCrewI {
    int getCurcol();
    int getCurrow();
    double getDeposit(Territory t);
    long getInterest(Territory t);
    long opponent(Territory t);
    long nearby(Territory t, Direction direction);
  
    Player ownerMoveTo(Direction direction, Territory t);

    void move(Direction direction, Territory t);
  
    void invest(long amount, Territory t);

    long collect(long amount, Territory t);

    void shoot(Direction direction, long amount, Territory t);
    Player owner(Territory t);
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
    public double getDeposit(Territory t) {
        return 0;
    }

    @Override
    public long getInterest(Territory t) {
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
    public Player ownerMoveTo(Direction direction, Territory t) {
        return null;
    }

    @Override
    public void move(Direction direction, Territory t) {

    }

    @Override
    public void invest(long amount,Territory t) {

    }

    @Override
    public long collect(long amount ,Territory t) {
        return 0;
    }

    @Override
    public void shoot(Direction direction, long amount,Territory t) {

    }

    @Override
    public Player owner(Territory t) {
        return null;
    }
}
