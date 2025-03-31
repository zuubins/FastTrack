package com.example.fasttrack;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class TrackingRoutine {
    private final Routine trackedRoutine;
    private final LocalDate startDate;
    private final LocalTime startTime;
    private LocalTime endTime;
    private final DBHelper dbHelper = new DBHelper(null);
    private ArrayList<Movement> trackedMovements;
    private static int completedID = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TrackingRoutine(Routine r) {
        startDate = LocalDate.now();
        startTime = LocalTime.now();
        trackedRoutine = r;
        trackedMovements = r.getRoutineList();

    }

    public void addMovement(Movement m) {

        trackedMovements.add(m);

    }

    public void removeMovement(Movement m) {

        trackedMovements.remove(m);

    }

    public void setWeight(@NonNull Movement m, int index1, int index2, int weight) {
        m.setWeight(index1,index2,weight);
    }

    public void setReps(@NonNull Movement m, int index1, int index2, int reps) {
        m.setReps(index1,index2,reps);
    }

    public void endWorkout() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            endTime = LocalTime.now();

            dbHelper.insertCompletedRoutine(completedID,startDate.toString(),null,
                    null,startTime.toString(),endTime.toString());
        }

        completedID++;

    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
