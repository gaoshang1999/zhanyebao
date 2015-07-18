package com.heverage.zhanyebao.client.db;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.heverage.zhanyebao.client.model.Group;
 
public class GroupSQLiteHelper extends SQLiteHelper {
 
 
    public GroupSQLiteHelper(Context context) {
        super(context);  
    }
  
    /**
     * CRUD operations (create "save", read "get", update, delete) group + get all groups + delete all groups
     */
 
    // Groups table name
    private static final String TABLE_NAME = "groups";
 
    // Groups Table Columns names

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USER_ID = "user_id";
 
    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_USER_ID};
 
    public void saveGroup(Group group){
        Log.d("addGroup", group.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
//        values.put(KEY_ID, group.getId());  
        values.put(KEY_NAME, group.getName());  
        values.put(KEY_USER_ID, group.getUserId());  
 
        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
        // 4. close
        db.close(); 
    }
 
    public Group queryGroup(int id){
 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build group object
        Group group = new Group();
        group.setId(cursor.getInt(0));
        group.setName(cursor.getString(1));
        group.setUserId(cursor.getInt(2));
 
        Log.d("getGroup("+id+")", group.toString());
 
        // 5. return group
        return group;
    }
 
    // Get All Groups
    public List<Group> queryAllGroups() {
        List<Group> groups = new LinkedList<Group>();
 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;
 
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build group and add it to list
        Group group = null;
        if (cursor.moveToFirst()) {
            do {
                group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));
                group.setUserId(cursor.getInt(2));
 
                // Add group to groups
                groups.add(group);
            } while (cursor.moveToNext());
        }
 
        Log.d("getAllGroups()", groups.toString());
 
        // return groups
        return groups;
    }
 
     // Updating single group
    public int updateGroup(Group group) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", group.getName()); // get title 
        values.put("user_id", group.getUserId()); // get author
 
        // 3. updating row
        int i = db.update(TABLE_NAME, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(group.getId()) }); //selection args
 
        // 4. close
        db.close();
 
        return i;
 
    }
 
    // Deleting single group
    public void deleteGroup(Group group) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_NAME,
                KEY_ID+" = ?",
                new String[] { String.valueOf(group.getId()) });
 
        // 3. close
        db.close();
 
        Log.d("deleteGroup", group.toString());
 
    }
    
    // Deleting all groups
    public void deleteAllGroups() {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_NAME,
                null,
                null);
 
        // 3. close
        db.close();

    }
}