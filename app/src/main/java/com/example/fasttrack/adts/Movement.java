package com.example.fasttrack.adts;

public class Movement extends Object {

    public int movementSets;
    public String movementName;
    public Movement() {
        movementSets = 1;
        movementName = "Unnamed Movement";
    }
    public Movement(String name, int sets) {
        movementName = name;
        movementSets = sets;
    }

    public void addSet() {
        movementSets++;
    }

    public void addSet(int addition) {
        movementSets += addition;
    }

    public void setName(String name) {
        movementName = name;
    }

    public String getName() {
        return movementName;
    }









}
