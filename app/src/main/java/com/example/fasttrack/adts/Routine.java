package com.example.fasttrack.adts;

import java.util.ArrayList;

public class Routine extends ArrayList<Movement> {
    private String routineName;
    private String targetGroup;
    private String routineType;

    public Routine () {
        routineName = "New Routine";
        routineType = "Unknown";
        targetGroup = "";
        ArrayList<Movement> routineList = new ArrayList<>();
    }

    public Routine (String name, String group, String type) {
        routineName = name;
        routineType = type;
        targetGroup = group;
        ArrayList<Movement> routineList = new ArrayList<>();
    }

    public void setRoutineName(String name) {
        routineName = name;
    }

    public String getRoutineName() {
        return routineName;
    }
    public void setTargetGroup(String group) {
        targetGroup = group;
    }
    public String getTargetGroup() {
        return targetGroup;
    }
    public void setRoutineType(String type) {
        routineType = type;
    }
    public String getRoutineType() {
        return routineType;
    }
    
}
