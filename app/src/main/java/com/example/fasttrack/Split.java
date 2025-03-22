package com.example.fasttrack;

import java.util.ArrayList;

public class Split {
    private String splitName;
    private String splitGoal;
    private int numberDays;
    ArrayList<Routine> splitRoutines;

    public Split() {
        splitGoal = "";
        splitName = "Unknown Split";
        numberDays = 0;
        splitRoutines = new ArrayList<>();
    }

    public Split(int days, String name, String goal) {
        splitGoal = goal;
        splitName = name;
        numberDays = days;
        splitRoutines = new ArrayList<>();

    }
    public ArrayList<Routine> getSplitRoutines() {
        return splitRoutines;
    }

    public void addRoutine(Routine r) {
        splitRoutines.add(r);
    }

    public void removeRoutine(Routine r) {
        splitRoutines.remove(r);
    }



}
