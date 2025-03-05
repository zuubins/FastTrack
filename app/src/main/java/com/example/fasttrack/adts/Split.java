package com.example.fasttrack.adts;

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

    public Split(int days) {

    }
    public ArrayList<Routine> getSplitRoutines() {
        return splitRoutines;
    }
    
}
