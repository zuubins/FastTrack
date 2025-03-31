package com.example.fasttrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "workoutDB";
    private static final int DATABASE_VERSION = 5; // Incremented database version to 5

    // Workout Splits Table
    private static final String TABLE_WORKOUT_SPLITS = "workout_splits";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DAYS = "days";
    private static final String COLUMN_ROUTINES = "routines";

    // Routines Table
    private static final String TABLE_ROUTINES = "routines";
    private static final String COLUMN_TARGET = "target";
    private static final String COLUMN_MOVEMENTS = "movements";
    private static final String COLUMN_SPLIT_ID = "split_id"; // Foreign key to workout_splits

    // Movements Table
    private static final String TABLE_MOVEMENTS = "movements";
    private static final String COLUMN_MOVEMENT_NAME = "name";
    private static final String COLUMN_TARGET_AREA = "target_area";
    private static final String COLUMN_MOVEMENT_FOCUS = "movement_focus";
    private static final String COLUMN_ROUTINE_ID = "routine_id"; // Foreign key to routines

    // Completed Routines Table
    private static final String TABLE_COMPLETED_ROUTINES = "completed_routines";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_WEIGHT_PER_SET = "weight_per_set";
    private static final String COLUMN_REPS_PER_SET = "reps_per_set";
    private static final String COLUMN_START_TIME = "start_time";  // New column for start time
    private static final String COLUMN_END_TIME = "end_time";  // New column for end time

    // Create table SQL query for workout_splits
    private static final String CREATE_TABLE_WORKOUT_SPLITS = "CREATE TABLE "
            + TABLE_WORKOUT_SPLITS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_DAYS + " INTEGER, "
            + COLUMN_ROUTINES + " TEXT"
            + ");";

    // Create table SQL query for routines
    private static final String CREATE_TABLE_ROUTINES = "CREATE TABLE "
            + TABLE_ROUTINES + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "name TEXT, "
            + COLUMN_TARGET + " TEXT, "
            + COLUMN_MOVEMENTS + " TEXT, "
            + COLUMN_SPLIT_ID + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_SPLIT_ID + ") REFERENCES " + TABLE_WORKOUT_SPLITS + "(" + COLUMN_ID + ")"
            + ");";

    // Create table SQL query for movements
    private static final String CREATE_TABLE_MOVEMENTS = "CREATE TABLE "
            + TABLE_MOVEMENTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MOVEMENT_NAME + " TEXT, "
            + COLUMN_TARGET_AREA + " TEXT, "
            + COLUMN_MOVEMENT_FOCUS + " TEXT, "
            + COLUMN_ROUTINE_ID + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_ROUTINE_ID + ") REFERENCES " + TABLE_ROUTINES + "(" + COLUMN_ID + ")"
            + ");";

    // Create table SQL query for completed_routines
    private static final String CREATE_TABLE_COMPLETED_ROUTINES = "CREATE TABLE "
            + TABLE_COMPLETED_ROUTINES + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ROUTINE_ID + " INTEGER, "
            + COLUMN_DATE + " TEXT, "
            + COLUMN_WEIGHT_PER_SET + " TEXT, "
            + COLUMN_REPS_PER_SET + " TEXT, "
            + COLUMN_START_TIME + " TEXT, "  // New column for start time
            + COLUMN_END_TIME + " TEXT, "   // New column for end time
            + "FOREIGN KEY(" + COLUMN_ROUTINE_ID + ") REFERENCES " + TABLE_ROUTINES + "(" + COLUMN_ID + ")"
            + ");";

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        db.execSQL(CREATE_TABLE_WORKOUT_SPLITS);
        db.execSQL(CREATE_TABLE_ROUTINES);
        db.execSQL(CREATE_TABLE_MOVEMENTS);
        db.execSQL(CREATE_TABLE_COMPLETED_ROUTINES);
    }

    // Called when the database version is incremented (e.g. during an app upgrade)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old tables and create new ones
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLETED_ROUTINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVEMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_SPLITS);
        onCreate(db);  // Recreate the tables
    }

    // Insert a new workout split into the database
    public long insertWorkoutSplit(String name, int days, String routines) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DAYS, days);
        values.put(COLUMN_ROUTINES, routines);

        // Insert the row and return the ID of the new record
        long id = db.insert(TABLE_WORKOUT_SPLITS, null, values);
        db.close(); // Close the database connection
        return id;
    }

    // Insert a new routine into the database
    public long insertRoutine(String name, String target, String movements, long splitId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put(COLUMN_TARGET, target);
        values.put(COLUMN_MOVEMENTS, movements);
        values.put(COLUMN_SPLIT_ID, splitId); // Foreign key reference to workout split

        // Insert the row and return the ID of the new record
        long id = db.insert(TABLE_ROUTINES, null, values);
        db.close(); // Close the database connection
        return id;
    }

    // Insert a new movement into the database
    public long insertMovement(String name, String targetArea, String movementFocus, long routineId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVEMENT_NAME, name);
        values.put(COLUMN_TARGET_AREA, targetArea);
        values.put(COLUMN_MOVEMENT_FOCUS, movementFocus);
        values.put(COLUMN_ROUTINE_ID, routineId); // Foreign key reference to routine

        // Insert the row and return the ID of the new record
        long id = db.insert(TABLE_MOVEMENTS, null, values);
        db.close(); // Close the database connection
        return id;
    }

    // Insert a completed routine into the database with start and end times
    public long insertCompletedRoutine(long routineId, String date, String weightPerSet, String repsPerSet, String startTime, String endTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROUTINE_ID, routineId);  // Foreign key reference to routine
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_WEIGHT_PER_SET, weightPerSet);
        values.put(COLUMN_REPS_PER_SET, repsPerSet);
        values.put(COLUMN_START_TIME, startTime);  // Insert start time
        values.put(COLUMN_END_TIME, endTime);    // Insert end time

        // Insert the row and return the ID of the new record
        long id = db.insert(TABLE_COMPLETED_ROUTINES, null, values);
        db.close(); // Close the database connection
        return id;
    }

    // Fetch all completed routines
    public Cursor getAllCompletedRoutines() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Query to get all records from the completed_routines table
        return db.query(TABLE_COMPLETED_ROUTINES, null, null, null, null, null, null);
    }

    // Fetch completed routines for a specific routine
    public Cursor getCompletedRoutinesByRoutineId(int routineId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ROUTINE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(routineId) };
        return db.query(TABLE_COMPLETED_ROUTINES, null, selection, selectionArgs, null, null, null);
    }

    // Update a completed routine entry
    public int updateCompletedRoutine(int id, long routineId, String date, String weightPerSet, String repsPerSet, String startTime, String endTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROUTINE_ID, routineId);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_WEIGHT_PER_SET, weightPerSet);
        values.put(COLUMN_REPS_PER_SET, repsPerSet);
        values.put(COLUMN_START_TIME, startTime);  // Update start time
        values.put(COLUMN_END_TIME, endTime);    // Update end time

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };
        return db.update(TABLE_COMPLETED_ROUTINES, values, whereClause, whereArgs);
    }

    // Delete a completed routine entry by ID
    public void deleteCompletedRoutine(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };
        db.delete(TABLE_COMPLETED_ROUTINES, whereClause, whereArgs);
        db.close();
    }

    private void populateSplitsTable() {
        insertWorkoutSplit("PPL", 3, "Push, Pull, Legs");
        insertWorkoutSplit("Full Body", 3, "Full Body");
        insertWorkoutSplit("Arnold", 3, "Chest/Back, Shoulders/Arms, Legs");
        insertWorkoutSplit("PPL (Push, Pull, Legs) - No Compounds", 3, "Push, Pull, Legs");
        insertWorkoutSplit("Upper / Lower", 4, "Upper, Lower");
        insertWorkoutSplit("PPL / Arnold", 5, "Push, Pull, Legs, Chest/Back, Shoulders/Arms");
        insertWorkoutSplit("6-day PPL (Push, Pull, Legs)", 6, "Push, Pull, Legs");
        insertWorkoutSplit("6-day PPL / Arnold", 6, "Push, Pull, Legs, Chest/Back, Shoulders/Arms");
    }

    private void populateRoutinesTable() {
        insertRoutine("Push", "Chest, Shoulders, Triceps", "", 1);
        insertRoutine("Pull", "Back, Biceps", "", 1);
        insertRoutine("Legs", "Legs", "", 1);
        insertRoutine("Full Body", "Full", "", 2);
        insertRoutine("Upper", "Chest, Shoulders, Triceps, Back, Biceps", "", 5);
        insertRoutine("Lower", "Legs, Core", "", 5);
        insertRoutine("Chest/Back", "Chest, Back", "", 3);
        insertRoutine("Shoulders/Arms", "Shoulders, Triceps, Biceps", "", 3);
    }

    private void populateMovementsTable() {
        
    }
}