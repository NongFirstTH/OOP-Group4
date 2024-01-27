package GamePlay;

import Grammar.Plan.Plan;

import java.util.List;

interface PlayerI {
    public List<Region> getRegions();
    public Region getCityCenter();
    public Plan getPlan();
    public String getName();
    public int getCurcol();
    public int getCurrow();
    public double getBudget();
    public int opponent();
    public int nearby(String direction);
    //returns true when relocatable (to end turn)
    public boolean relocate();
    //returns true when not have enough budget
    public boolean move();
    public void invest(double amount);
    //returns true when not have enough budget
    public boolean collect(double amount);
    public void shoot(String direction, double  amount);
}

public class Player implements PlayerI {
    private String name;
    private List<Region> regions;
    private Region cityCenter;
    private CityCrew crew;
    private double budget;
    private Plan plan;

    @Override
    public List<Region> getRegions() {
        return regions;
    }

    @Override
    public Region getCityCenter() {
        return cityCenter;
    }

    @Override
    public Plan getPlan() {
        return plan;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCurcol() {
        return crew.getCurcol();
    }

    @Override
    public int getCurrow() {
        return crew.getCurrow();
    }

    @Override
    public double getBudget() {
        return budget;
    }

    @Override
    public int opponent() {
        return 0;
    }

    @Override
    public int nearby(String direction) {

        return 0;
    }

    @Override
    public boolean relocate() {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public void invest(double amount) {

    }

    @Override
    public boolean collect(double amount) {
        return false;
    }

    @Override
    public void shoot(String direction, double amount) {

    }
}
