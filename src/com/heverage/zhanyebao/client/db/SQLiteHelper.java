package com.heverage.zhanyebao.client.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SQLiteHelper extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "zybDB";
 
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
   
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create group table
        String CREATE_GROUP_TABLE = "CREATE TABLE groups ( " +
        		"id integer primary key autoincrement," +
                "name TEXT, "+
                "user_id INTEGER  " + 
                " )";
        
        String CREATE_Client_TABLE = "CREATE TABLE clients ( " +
        		"id integer primary key autoincrement," +
                "json TEXT "+
                " )";
 
        // create groups table
        db.execSQL(CREATE_GROUP_TABLE);
        db.execSQL(CREATE_Client_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older groups table if existed
    	db.execSQL("DROP TABLE IF EXISTS groups");
        db.execSQL("DROP TABLE IF EXISTS clients");
 
        // create fresh groups table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------
 
}