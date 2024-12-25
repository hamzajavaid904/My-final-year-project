package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text, Email text,password text)";
        db.execSQL(qry1);

        String cartqry = "create table cart(Email text,product text,price float,otype text)";
        db.execSQL(cartqry);

        String orddetqry = "create table orderplace(Email text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(orddetqry);

        String orderqry = "create table orderpalce2(Email text,fullname text, address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(orderqry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String Email, String Password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", Email);
        cv.put("password", Password);
        SQLiteDatabase mydb = getWritableDatabase();
        mydb.insert("users", null, cv);
        mydb.close();
    }

    public int login(String Email, String Password) {
        int result = 0;
        String[] str = new String[2];
        str[0] = Email;
        str[1] = Password;
        SQLiteDatabase mydb = getReadableDatabase();
        Cursor c = mydb.rawQuery("select * from users where Email =? and Password=?", str);

        if (c.moveToFirst()) {
            result = 1;
        }

        return result;
    }

    public void cart(String Email, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("Email", Email);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();

    }

    public int checkcart(String Email, String product) {

        int result = 0;
        String str[] = new String[2];
        str[0] = Email;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor check = db.rawQuery("select * from cart where Email = ? and product =?", str);
        if (check.moveToFirst()) {
            result = -1;
        }
        return result;
    }

    public void delete(String Email, String otype) {

        String str[] = new String[2];
        str[0] = Email;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "Email = ? and otype = ?", str);
        db.close();
    }

    public ArrayList getdata(String Email, String otype) {

        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = Email;
        str[1] = otype;
        Cursor check = db.rawQuery("select * from cart where Email = ? and otype = ? ", str);
        if (check.moveToFirst()) {
            do {
                String product = check.getString(1);
                String price = check.getString(2);
                arrayList.add(product + "$" + price);
            }
            while (check.moveToNext());

        }
        db.close();
        return arrayList;
    }

    public void addorder(String Email, String fullname, String address, String contactno, int pincode, String date, String time, float amount, String otype) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", Email);
        contentValues.put("fullname", fullname);
        contentValues.put("address", address);
        contentValues.put("contactno", contactno);
        contentValues.put("pincode", pincode);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("amount", amount);
        contentValues.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, contentValues);
        db.close();

    }

    public ArrayList getorderdata(String Email)
    {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = Email;
        Cursor cursor =db.rawQuery("select * from orderplace where Email = ?",str);
        if (cursor.moveToFirst()){
            do {
                arr.add(cursor.getString(1)+"$"+cursor.getString(2)+"$"+cursor.getString(3)+"$"+cursor.getString(4)+"$"+cursor.getString(5)+"$"+cursor.getString(6)+"$"+cursor.getString(7)+"$"+cursor.getString(8));
            }
            while (cursor.moveToNext());
        }

        return arr;
    }

    public int checkappionmetexist(String Email,String fullname,String address,String contactno,String date,String time){
        int result = 0;
        String str [] = new String[6];
        str [0] = Email;
        str [1] = fullname;
        str [2] = address;
        str [3] = contactno;
        str [4] = date;
        str [5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderpalce2 where Email=? and fullname=? and address=? and contactno=? and date=? and time=?",str);
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;

    }

    }
