package com.example.fasttrack;

public class Movement extends Object {

    public int movementSets;
    private String movementName;
    private String movementType;
    private String movementTarget;
    private int[][] weightRepsArr;
    public Movement(String name) {
        movementSets = 1;
        movementName = name;
        movementType = "Unknown";
        movementTarget = "";
        weightRepsArr = new int[4][4];
    }
    public Movement(String name, int sets, String type, String target) {
        movementName = name;
        movementSets = sets;
        movementType = type;
        movementTarget = target;
        weightRepsArr = new int[4][4];
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

    public int[][] getMoveSet() {
        return weightRepsArr;
    }

    public void setWeight(int index1,int index2, int weight) {
        weightRepsArr[index1][index2] = weight;
    }

    public void setReps(int index1, int index2, int reps) {
        weightRepsArr[index1][index2] = reps;
    }
}
