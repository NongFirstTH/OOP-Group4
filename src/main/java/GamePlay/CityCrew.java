package GamePlay;

import Grammar.Plan.Direction;

interface CityCrewI {
    int getCurcol();
    int getCurrow();
    double getDeposit();
    long getInterest();
    long getMaxDeposit();
    int opponent(Territory t);
    int nearby(Territory t, Direction direction);
    void relocate();
  
    Player ownerMoveTo(Direction direction, Territory t);

    void move(Direction direction, Territory t);
  
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
    public long getInterest() {
        return 0;
    }

    @Override
    public long getMaxDeposit() {
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
    public Player ownerMoveTo(Direction direction, Territory t) {
        return null;
    }

    @Override
    public void move(Direction direction, Territory t) {

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
