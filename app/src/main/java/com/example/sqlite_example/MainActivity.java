package com.example.sqlite_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    SQLiteDatabase DB;
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = (TextView)findViewById(R.id.status);

        DBhelperEx dBhelperEx = new DBhelperEx(this, "DB", null, 1);
        //쓰기 가능한  SQLiteDatabase 인스턴스
        DB = dBhelperEx.getWritableDatabase();

        //insertDB("SIT_UP", 10);
        //searchDB("SIT_UP");
        //insertDB("SIT_UP", 10);
        //searchDB("SIT_UP", 2);
        //updateDB("SIT_UP", 10, 13);
        //deleteDB("SIT_UP", 53);
        //searchDB("SIT_UP");
        deleteDB("SIT_UP");
        insertDB("SIT_UP", 11);
        searchDB("SIT_UP");

    }

    //삽입
    void insertDB(String tableName, Integer count) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NUMBER", count);

        long newRowId = DB.insert(tableName, null, contentValues);
    }
    //테이블전체 훑음
    void searchDB(String tableName) {

        Cursor cursor = null;

        try {
           cursor = DB.query(tableName, null, null, null, null, null, null);
           if(cursor != null) {
               while(cursor.moveToNext()) {
                   String id = cursor.getString(cursor.getColumnIndex("ID"));
                   String number = cursor.getString(cursor.getColumnIndex("NUMBER"));
                   temp.setText(id + "번째 도전: " + number + "회");
               }
           }
        } finally {
            if(cursor != null){
                cursor.close();
            }
        }

    }
    //특정한 무언가를 찾아줌
    void searchDB(String tableName, Integer id) {

        String sqlSelect = "SELECT * FROM " + tableName + " WHERE ID = ?";
        Cursor cursor = DB.rawQuery(sqlSelect, new String[] {Integer.toString(id)});

        while(cursor.moveToNext()) {
            int number = cursor.getInt(cursor.getColumnIndex("NUMBER"));
            String ID = cursor.getString(cursor.getColumnIndex("ID"));
            temp.setText(ID + "번째 도전: " + Integer.toString(number) + "회");
        }

    }
    //데이터를 수정해줌
    void updateDB(String tableName, int id, int data){
        String sqlUpdate = "UPDATE " + tableName + " SET NUMBER=? WHERE ID = ?" ;
        DB.execSQL(sqlUpdate, new String[] {Integer.toString(data), Integer.toString(id)});
        searchDB("SIT_UP", id);
    }
    //하나를 찾아서 삭제해줌
    void deleteDB(String tableName, int id) {
        String sqlDelete = "DELETE FROM SIT_UP WHERE ID=?";
        DB.execSQL(sqlDelete, new String[] {Integer.toString(id)});
    }
    //전부 삭제해줌
    void deleteDB(String tableName) {
        String sqlDelete = "DELETE FROM SIT_UP";
        DB.execSQL(sqlDelete);
    }

}

/*import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;*/
/*
    TextView textText;
    //테이블제작
    SQLiteDatabase sqlDB = null;
        try{
                sqlDB = SQLiteDatabase.openOrCreateDatabase("sample.db", null);
                } catch (SQLiteException e){
                e.printStackTrace();
                }

                String command = "CREATE TABLE IF NOT EXISTS ORDER_T(NUM integer, NAME text)" ;
                sqlDB.execSQL(command);
*/


/*
SQLiteDatabase sqliteDB = null;
        try{
            sqliteDB = SQLiteDatabase.openOrCreateDatabase("sample.db", null);

        } catch(SQLiteException e) {
            e.printStackTrace();
        }

        String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS ORDER_T (NUMBER INTEGER, NAME TEXT)" ;
        sqliteDB.execSQL(sqlCreateTbl);
        String sqlInsert = "INSERT OR REPLACE INTO ORDER_T (NUM, NAME) VALUES (1, 'ppotta')";
        sqliteDB.execSQL(sqlInsert);
        String sqlUpdate = "UPDATE ORDER_T SET NUMBER=2, NAME='ppotta2' WHERE NUMBER=1";
        sqliteDB.execSQL(sqlUpdate);
        //String sqlDelete = "DELETE FROM ORDER_T WHERE NUMBER=2";
        //sqliteDB.execSQL(sqlDelete);

        String sqlSelect = "SELECT * FROM ORDER_T" ;
        Cursor cursor;

        cursor = sqliteDB.rawQuery(sqlSelect, null);
        textText = (TextView)findViewById(R.id.textText);

        while(cursor.moveToNext()) {
            int no = cursor.getInt(0);

            String name = cursor.getString(1);

            //textText.setText(String.format("%d", no));
        }


        cursor.close();

    }
*/