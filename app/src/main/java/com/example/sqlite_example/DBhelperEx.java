package com.example.sqlite_example;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBhelperEx extends  SQLiteOpenHelper {

    public DBhelperEx(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String createTable = "CREATE TABLE SIT_UP " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NUMBER INTEGER );";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        String dropTable = "DROP TABLE IF EXISTS SIT_UP;";
        sqLiteDatabase.execSQL(dropTable);
    }

}
