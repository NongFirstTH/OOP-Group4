package GamePlay;
import Grammar.Expression.EvalError;
import Grammar.Plan.Direction;

interface CityCrewI {
    int getCurcol();
    int getCurrow();
    double getDeposit(Territory t);
    long getInterest(double baseInterestRate, int currentTurn, Territory t);
    long opponent(Player p, Territory t) throws EvalError;
    long nearby(Territory t, Direction direction,Player p) throws EvalError;
  
    Player ownerMoveTo(Direction direction, Territory t) throws EvalError;

    void move(Direction direction, Territory t) throws EvalError;
  
    void invest(long amount, Territory t, Player p, long maxDeposit) throws EvalError;

    long collect(long amount, Game g);

    void shoot(Direction direction, long amount, Game g) throws EvalError;

    Player getOwner(Territory t);
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
        return t.getRegions(currow,curcol).getDeposit();
    }

    @Override
    public long getInterest(double baseInterestRate, int currentTurn, Territory t) {
        return t.getRegions(currow,curcol).getInterest(baseInterestRate, currentTurn);
    }

    private long distance(CityCrew c,Direction direction,Player p,Territory t) throws EvalError {
        long x = 0;
        Player owner = t.getOwner(c.currow,c.curcol);
        while ((owner == null || owner == p) && c.isMove(direction,t)){
            x++;
            owner = t.getOwner(c.currow,c.curcol);
        }
        if(owner == p||owner == null){
            return Long.MAX_VALUE;
        }
        return x;
    }

    @Override
    public long opponent(Player p , Territory t) throws EvalError {
        long opponent = 0;
        for (Direction dir : Direction.values()){
            CityCrew c = new CityCrew(currow,curcol);
            long distance = distance(c,dir,p,t);
            if(distance < Long.MAX_VALUE){
                long direction = dir.ordinal()+1+distance*10;
                if(direction < opponent || opponent == 0) opponent = direction;
            }
        }
        return opponent;
    }

    @Override
    public long nearby(Territory t, Direction direction,Player p) throws EvalError {
        CityCrew c = new CityCrew(currow,curcol);
        long x = distance(c,direction,p,t);
        double opponentDeposit = t.getRegions(c.currow,c.curcol).getDeposit();
        long y = opponentDeposit == 0 ? 0 : (long) (1 + Math.log10(opponentDeposit));
        if(x==Long.MAX_VALUE) return 0;
        return (long) (100L *x + y);
    }

    @Override
    public Player ownerMoveTo(Direction direction, Territory t) throws EvalError {
        CityCrew c = new CityCrew(currow,curcol);
        c.move(direction,t);
        return t.getOwner(c.currow,c.curcol);
    }

    @Override
    public void move(Direction direction, Territory t) throws EvalError {
        isMove(direction,t);
    }

    private boolean isMove(Direction direction, Territory t) throws EvalError {
        int moverow = currow;
        int movecol = curcol;

        switch (direction){
            case up -> {
                moverow = currow-1;
            }
            case upright -> {
                if(curcol%2==0) moverow = currow-1;
                movecol = curcol+1;
            }
            case downright -> {
                if(curcol%2==1) moverow = currow+1;
                movecol = curcol+1;
            }
            case down -> {
                moverow = currow+1;
            }
            case downleft -> {
                if(curcol%2==1) moverow = currow+1;
                movecol = curcol-1;
            }
            case upleft -> {
                if(curcol%2==0) moverow = currow-1;
                movecol = curcol-1;
            }
            default -> throw new EvalError("unknown direction " + direction);
        }
        if(t.getRegions(moverow,movecol)!=null){
            currow = moverow;
            curcol = movecol;
            return true;
        }
        return false;
    }

    private  boolean checkCrewAdjacent(Player p,Territory t) throws EvalError {
        Player owner = getOwner(t);
        if (owner!=null && owner.equals(p))
            return true;
        for(Direction direction : Direction.values()){
            CityCrew c = new CityCrew(currow,curcol);
            if (c.isMove(direction,t)) {
                Player owner2 = t.getRegions(c.currow, c.curcol).getOwner();
                if (owner2 != null && owner2.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void invest(long amount,Territory t, Player p, long maxDeposit) throws EvalError {
        if((checkCrewAdjacent(p,t))){
            t.getRegions(currow,curcol).beInvested(amount, p, maxDeposit);
        }
    }

    @Override
    public long collect(long amount ,Game g) {
        return g.getTerritory().getRegions(currow,curcol).beCollected(amount, g);
    }

    @Override
    public void shoot(Direction direction, long amount, Game g) throws EvalError {
        int shootrow = currow;
        int shootcol = curcol;
        switch (direction){
            case up -> {
                shootrow = currow-1;
            }
            case upright -> {
                if(curcol%2==0) shootrow = currow-1;
                shootcol = curcol+1;
            }
            case downright -> {
                if(curcol%2==1) shootrow = currow+1;
                shootcol = curcol+1;
            }
            case down -> {
                shootrow = currow+1;
            }
            case downleft -> {
                if(curcol%2==1) shootrow = currow+1;
                shootcol = curcol-1;
            }
            case upleft -> {
                if(curcol%2==0) shootrow = currow-1;
                shootcol = curcol-1;
            }
            default -> throw new EvalError("unknown direction " + direction);
        }
        Region r = g.getTerritory().getRegions(shootrow,shootcol);
        if(r!=null){
            r.beShot(amount, g);
        }
    }

    @Override
    public Player getOwner(Territory t) {
        return t.getOwner(currow,curcol);
    }
}
