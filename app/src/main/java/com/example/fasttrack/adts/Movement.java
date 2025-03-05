package com.example.fasttrack.adts;

public class Movement extends Object {

    public int movementSets;
    private String movementName;
    private String movementType;
    private String movementTarget;
    public Movement() {
        movementSets = 1;
        movementName = "Unnamed Movement";
        movementType = "Unknown";
        movementTarget = "";
    }
    public Movement(String name, int sets, String type, String target) {
        movementName = name;
        movementSets = sets;
        movementType = type;
        movementTarget = target;
    }

    public void addSet() {
        movementSets++;
    }

    public void addSet(int addition) {
        movementSets += addition;
    }
    public int getMovementSets() {
        return movementSets;
    }

    public void setName(String name) {
        movementName = name;
    }

    public String getName() {
        return movementName;
    }









}
