package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String name = "Healthcaredb";

    public DBhelper(Context context) {
        super(context, "Healthcaredb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {

        mydb.execSQL("create Table users(Usename TEXT ,Email TEXT,Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {
        mydb.execSQL("drop Table if exists users");
    }

    public Boolean insertdata(String username, String Email, String Password) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("Email", Email);
        cv.put("Password", Password);
        long result = mydb.insert("users", null, cv);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkusername(String Email) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        String[] str = new String[]{Email};
        Cursor cursor = mydb.rawQuery("select * from users where Email = ?", str);
     if (cursor.getCount()>0) return true;
     else return false;
    }

    public Boolean checkusernamepassword(String Email,String Password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        String[] str = new String[]{Email,Password};
        Cursor cursor = mydb.rawQuery("select * from users where Email =? and Password=?",str);
        if (cursor.getCount() > 0) return false;
        else return true;

    }
}
