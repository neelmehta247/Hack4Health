package me.neelmehta.hack4health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by rahuldominic on 05/11/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tasksManager";

    // Tasks table name
    private static final String TABLE_TASKS = "tasks";

    // Tasks Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_TASK_TIME = "task_time";

    private static DatabaseHandler mSingleton;

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DatabaseHandler getSingleton(Context context) {
        if (mSingleton == null)
            mSingleton = new DatabaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
        return mSingleton;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_TIMESTAMP + " INTEGER, " + KEY_TASK_TIME + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Adding new task
    public void addTask(TaskModal task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_TIMESTAMP, task.getTimestamp());
        values.put(KEY_TASK_TIME, task.getTimeInMilliseconds());

        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    // Getting single task
    public TaskModal getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[]{KEY_ID,
                        KEY_NAME, KEY_TIMESTAMP, KEY_TASK_TIME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TaskModal result = new TaskModal(cursor.getLong(0),
                cursor.getString(1), cursor.getLong(3), cursor.getLong(2));

        return result;
    }

    // Getting all tasks
    public ArrayList<TaskModal> getAllTasks() {
        ArrayList<TaskModal> tasks = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TaskModal task = new TaskModal();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setTimestamp(cursor.getLong(2));
                task.setTimeInMilliseconds(cursor.getLong(3));
                // Adding contact to list
                tasks.add(task);
            } while (cursor.moveToNext());
        }

        // return contact list
        return tasks;
    }

    // Updating single task
    public int updateTask(TaskModal task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_TIMESTAMP, task.getTimestamp());
        values.put(KEY_TASK_TIME, task.getTimeInMilliseconds());

        // updating row
        int result = db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
        return result;
    }

}
