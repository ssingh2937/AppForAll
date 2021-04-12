package com.sandhu.appforall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Grades.db"; // database name
    public static final String TABLE_NAME = "grade_table"; // table name

    //Columns
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PROGRAMCODE";
    public static final String COL_4 = "GRADE";
    public static final String COL_5 = "DURATION";
    public static final String COL_6 = "FEES";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PROGRAMCODE TEXT, GRADE INTEGER, DURATION TEXT, FEES INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //insert grades
    public boolean insertData(String name, String programCode, String grade, String duration, String fees) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, programCode);
        contentValues.put(COL_4, grade);
        contentValues.put(COL_5, duration);
        contentValues.put(COL_6, fees);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    //get grades
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //search grades by search id
    public Cursor searchById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID = " + id, null);
        return res;
    }

    //search grades by program code
    public Cursor searchByPCode(String pCode){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where PROGRAMCODE = " + pCode, null);
        return res;
    }
}
