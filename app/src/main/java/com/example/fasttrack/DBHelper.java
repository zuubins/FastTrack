package com.example.fasttrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "fastTrack.db";
    private static final int DATABASE_VERSION = 1;

    // Splits Table
    public static final String TABLE_WORKOUT_SPLITS = "workout_splits";
    public static final String COLUMN_WORKOUT_SPLIT_ID = "id";
    public static final String COLUMN_NAME = "name";  // e.g., "Push/Pull/Legs"

    // Routines Table
    public static final String TABLE_DAILY_ROUTINES = "daily_routines";
    public static final String COLUMN_DAILY_ROUTINE_ID = "id";
    public static final String COLUMN_SPLIT_ID = "split_id";  // Foreign Key to workout_splits
    public static final String COLUMN_DAY_OF_WEEK = "day_of_week"; // e.g., "Monday"
    public static final String COLUMN_ROUTINE_NAME = "routine_name";  // e.g., "Chest Day"

    // Movements Table
    public static final String TABLE_MOVEMENTS = "movements";
    public static final String COLUMN_MOVEMENT_ID = "id";
    public static final String COLUMN_DAILY_ROUTINE_ID = "daily_routine_id";  // Foreign Key to daily_routines
    public static final String COLUMN_EXERCISE_NAME = "exercise_name";  // e.g., "Bench Press"
    public static final String COLUMN_SETS = "sets";  // e.g., "3"
    public static final String COLUMN_REPS = "reps";  // e.g., "10"
    public static final String COLUMN_WEIGHT = "weight";  // Optional

    // SQL to create tables
    private static final String CREATE_WORKOUT_SPLITS_TABLE =
            "CREATE TABLE " + TABLE_WORKOUT_SPLITS + " (" +
                    COLUMN_WORKOUT_SPLIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT);";

    private static final String CREATE_DAILY_ROUTINES_TABLE =
            "CREATE TABLE " + TABLE_DAILY_ROUTINES + " (" +
                    COLUMN_DAILY_ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SPLIT_ID + " INTEGER, " +
                    COLUMN_DAY_OF_WEEK + " TEXT, " +
                    COLUMN_ROUTINE_NAME + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_SPLIT_ID + ") REFERENCES " + TABLE_WORKOUT_SPLITS + "(" + COLUMN_WORKOUT_SPLIT_ID + "));";

    private static final String CREATE_MOVEMENTS_TABLE =
            "CREATE TABLE " + TABLE_MOVEMENTS + " (" +
                    COLUMN_MOVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DAILY_ROUTINE_ID + " INTEGER, " +
                    COLUMN_EXERCISE_NAME + " TEXT, " +
                    COLUMN_SETS + " INTEGER, " +
                    COLUMN_REPS + " INTEGER, " +
                    COLUMN_WEIGHT + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_DAILY_ROUTINE_ID + ") REFERENCES " + TABLE_DAILY_ROUTINES + "(" + COLUMN_DAILY_ROUTINE_ID + "));";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORKOUT_SPLITS_TABLE);
        db.execSQL(CREATE_DAILY_ROUTINES_TABLE);
        db.execSQL(CREATE_MOVEMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVEMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILY_ROUTINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_SPLITS);
        onCreate(db);
    }

    // Insert a new workout split
    public long insertWorkoutSplit(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        return db.insert(TABLE_WORKOUT_SPLITS, null, values);
    }

    // Insert a new daily routine for a specific workout split
    public long insertDailyRoutine(long splitId, String dayOfWeek, String routineName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPLIT_ID, splitId);
        values.put(COLUMN_DAY_OF_WEEK, dayOfWeek);
        values.put(COLUMN_ROUTINE_NAME, routineName);
        return db.insert(TABLE_DAILY_ROUTINES, null, values);
    }

    // Insert a new movement/exercise for a daily routine
    public long insertMovement(long dailyRoutineId, String exerciseName, int sets, int reps, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAILY_ROUTINE_ID, dailyRoutineId);
        values.put(COLUMN_EXERCISE_NAME, exerciseName);
        values.put(COLUMN_SETS, sets);
        values.put(COLUMN_REPS, reps);
        values.put(COLUMN_WEIGHT, weight);
        return db.insert(TABLE_MOVEMENTS, null, values);
    }

    // Retrieve all workout splits
    public Cursor getAllWorkoutSplits() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_WORKOUT_SPLITS,
                new String[]{COLUMN_WORKOUT_SPLIT_ID, COLUMN_NAME},
                null, null, null, null, null);
    }

    // Retrieve all daily routines for a specific split
    public Cursor getDailyRoutinesForSplit(long splitId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_DAILY_ROUTINES,
                new String[]{COLUMN_DAILY_ROUTINE_ID, COLUMN_DAY_OF_WEEK, COLUMN_ROUTINE_NAME},
                COLUMN_SPLIT_ID + "=?",
                new String[]{String.valueOf(splitId)}, null, null, null);
    }

    // Retrieve all movements for a specific daily routine
    public Cursor getMovementsForDailyRoutine(long dailyRoutineId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MOVEMENTS,
                new String[]{COLUMN_MOVEMENT_ID, COLUMN_EXERCISE_NAME, COLUMN_SETS, COLUMN_REPS, COLUMN_WEIGHT},
                COLUMN_DAILY_ROUTINE_ID + "=?",
                new String[]{String.valueOf(dailyRoutineId)}, null, null, null);
    }
}