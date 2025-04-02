package com.example.fasttrack;

import java.util.ArrayList;

public class Routine {
    private String routineName;
    private String targetGroup;
    private String routineType;
    ArrayList<Movement> routineList = new ArrayList<>();
    public Routine () {
        routineName = "New Routine";
        routineType = "Unknown";
        targetGroup = "";
        routineList = new ArrayList<>();
    }

    public Routine (String name, String group, String type) {
        routineName = name;
        routineType = type;
        targetGroup = group;
        routineList = new ArrayList<>();
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
    public ArrayList<Movement> getRoutineList() {
        return routineList;
    }

    public void addMovement(Movement m) {
        routineList.add(m);

    }

    public void removeMovement(int index) {
        routineList.remove(index);

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getRoutineName() + "\n");
        for( Movement movement: routineList) {
            s.append(movement.getName());

        }
        return s.toString();
    }



}
