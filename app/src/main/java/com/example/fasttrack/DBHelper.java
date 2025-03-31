package com.example.fasttrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String dbName = "fastTrack.db";
    private static final int dbVersion = 1;

    // Splits Table
    public static final String splitsTableName = "workout_splits";
    public static final String columnSplitID = "id";
    public static final String columnSplitName = "name";

    // Routines Table
    public static final String routinesTableName = "daily_routines";
    public static final String columnRoutineID = "id";
    public static final String COLUMN_SPLIT_ID = "split_id";  // Foreign Key to workout_splits
    public static final String columnDayOfWeek = "day_of_week"; // e.g., "Monday"
    public static final String columnRoutineName = "routine_name";  // e.g., "Chest Day"

    // Movements Table
    public static final String TABLE_MOVEMENTS = "movements";
    public static final String COLUMN_MOVEMENT_ID = "id";
    public static final String COLUMN_MOVEMENT_NAME = "exercise_name";  // e.g., "Bench Press"
    public static final String COLUMN_SETS = "sets";  // e.g., "3"
    public static final String COLUMN_REPS = "reps";  // e.g., "10"
    public static final String COLUMN_WEIGHT = "weight";  // Optional

    public static final String TABLE_COMPLETED_WORKOUTS = "completed_workouts";
    public static final String COLUMN_COMPLETED_WORKOUT_ID = "id";
    public static final String COLUMN_START_TIME = "start_time";  // Timestamp for workout start
    public static final String COLUMN_END_TIME = "end_time";  // Timestamp for workout end
    public static final String COLUMN_DATE = "date";  // Date of workout

    // SQL to create tables
    private static final String CREATE_WORKOUT_SPLITS_TABLE =
            "CREATE TABLE " + splitsTableName + " (" +
                    columnSplitID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columnSplitName + " TEXT);";

    private static final String CREATE_DAILY_ROUTINES_TABLE =
            "CREATE TABLE " + routinesTableName + " (" +
                    columnRoutineID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SPLIT_ID + " INTEGER, " +
                    columnDayOfWeek + " TEXT, " +
                    columnRoutineName + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_SPLIT_ID + ") REFERENCES " + splitsTableName +
                    "(" + columnSplitID + "));";

    private static final String CREATE_MOVEMENTS_TABLE =
            "CREATE TABLE " + TABLE_MOVEMENTS + " (" +
                    COLUMN_MOVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MOVEMENT_NAME + " TEXT, " +
                    COLUMN_SETS + " INTEGER, " +
                    COLUMN_REPS + " INTEGER, " +
                    COLUMN_WEIGHT + " TEXT, " +
                    "FOREIGN KEY(" + columnRoutineID + ") REFERENCES " + routinesTableName +
                    "(" + columnRoutineID + "));";

    private static final String CREATE_COMPLETED_WORKOUTS_TABLE =
            "CREATE TABLE " + TABLE_COMPLETED_WORKOUTS + " (" +
                    COLUMN_COMPLETED_WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columnSplitID + " INTEGER, " +
                    COLUMN_START_TIME + " INTEGER, " +  // Store timestamp
                    COLUMN_END_TIME + " INTEGER, " +    // Store timestamp
                    COLUMN_DATE + " TEXT, " +           // Store date as text
                    "FOREIGN KEY(" + columnSplitID + ") REFERENCES " + splitsTableName + "(" + columnSplitID + "));";

    public DBHelper(Context context) {
        super(context, dbName, null, dbVersion);
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
        db.execSQL("DROP TABLE IF EXISTS " + routinesTableName);
        db.execSQL("DROP TABLE IF EXISTS " + splitsTableName);
        onCreate(db);
    }

    // Insert a new workout split
    public long insertWorkoutSplit(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnSplitName, name);
        return db.insert(splitsTableName, null, values);
    }

    // Insert a new daily routine for a specific workout split
    public long insertDailyRoutine(long splitId, String dayOfWeek, String routineName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPLIT_ID, splitId);
        values.put(columnDayOfWeek, dayOfWeek);
        values.put(columnRoutineName, routineName);
        return db.insert(routinesTableName, null, values);
    }

    // Insert a new movement/exercise for a daily routine
    public long insertMovement(long dailyRoutineId, String exerciseName, int sets, int reps, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVEMENT_NAME, exerciseName);
        values.put(COLUMN_SETS, sets);
        values.put(COLUMN_REPS, reps);
        values.put(COLUMN_WEIGHT, weight);
        return db.insert(TABLE_MOVEMENTS, null, values);
    }

    // Retrieve all workout splits
    public Cursor getAllWorkoutSplits() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(splitsTableName,
                new String[]{columnSplitID, columnSplitName},
                null, null, null, null, null);
    }

    // Retrieve all daily routines for a specific split
    public Cursor getDailyRoutinesForSplit(long splitId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(routinesTableName,
                new String[]{columnRoutineID, columnDayOfWeek, columnRoutineName},
                COLUMN_SPLIT_ID + "=?",
                new String[]{String.valueOf(splitId)}, null, null, null);
    }

    // Retrieve movements for a specific daily routine
    public Cursor getMovementsForDailyRoutine(long dailyRoutineId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MOVEMENTS,
                new String[]{COLUMN_MOVEMENT_ID, COLUMN_MOVEMENT_NAME, COLUMN_SETS, COLUMN_REPS, COLUMN_WEIGHT},
                columnRoutineID + "=?",
                new String[]{String.valueOf(dailyRoutineId)}, null, null, null);
    }

    public long insertCompletedRoutine(long splitId, String dayOfWeek, String routineName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPLIT_ID, splitId);
        values.put(columnDayOfWeek, dayOfWeek);
        values.put(columnRoutineName, routineName);
        return db.insert(routinesTableName, null, values);
    }
}