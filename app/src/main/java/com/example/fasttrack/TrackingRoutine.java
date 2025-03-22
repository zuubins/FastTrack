package com.example.fasttrack;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TrackingRoutine {
    private Context context;
    private Routine trackedRoutine;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private DBHelper dbHelper = new DBHelper(context);
    private ArrayList<Movement> trackedMovements;

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

    public void endWorkout() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            endTime = LocalTime.now();

            dbHelper.insertCompletedRoutine(1234, LocalDate.now().toString(), trackedRoutine.getRoutineName());
        }

    }


}
