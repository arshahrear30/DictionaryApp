package com.arshahrear.dictonaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

// public class DatabaseHelper এর পর extends এর পর SQLiteAssetHelper লিখবো যদি database file আগে বানিয়ে main(Project) এ upload করে কাজ করি
//Red bulb >> create constructor >>ok
//Context context রেখে বাকীগুলো বাদ
//super(context, name দিবো same as my uploded file in main(Project), storageDirectory কেটে দাও, factory কেটে দেও তার পরিবর্তে null লিখো, version আমরা 1bদরলাম);
// যেহেতু এখানে ডাটা ইনপুট দেওয়া লাগছে না , আগে থেকেই ফাইলে আছে তাই onCreate অথবা onUpdate মেথডটি প্রয়োজন নেই
// এখন আমরা একটি মেথড তৈরি করব যেটা দিয়ে data গুলোকে Listview তে শো করাবো
public class DatabaseHelper extends SQLiteAssetHelper {
    public DatabaseHelper(Context context) {
        super(context, "dictionary.db", null, 1);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dictionary", null);
        return cursor;

    }

    public Cursor searchData(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dictionary where word like '%" +key+ "%'", null);
        //আগে পরে % দেওয়ার কারণে যেকোনোভাবে search করলে খুজে পাওয়া যাবে হুবহু মিলতে হবে না
        return cursor;

    }


}
