package com.example.fasttrack;

public class MovementSet {
    public int repsDone;
    public int weightUsed;
    public String notes;

    public MovementSet(Movement move) {
        repsDone = 0;
        weightUsed = 0;
        notes = "";

    }
    public void setWeightUsed(int weight) {
        weightUsed = weight;
    }
    public int getWeightUsed() {
        return weightUsed;
    }
    public void setRepsDone(int reps) {
        repsDone = reps;
    }

}
