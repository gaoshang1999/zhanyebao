package com.heverage.zhanyebao.client.db;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.heverage.zhanyebao.client.model.Client;
 
public class ClientSQLiteHelper extends SQLiteHelper {
 
 
    public ClientSQLiteHelper(Context context) {
        super(context);  
    }
  
    /**
     * CRUD operations (create "save", read "get", update, delete) client + get all clients + delete all clients
     */
 
    // Clients table name
    private static final String TABLE_NAME = "clients";
 
    // Clients Table Columns names

    private static final String KEY_ID = "id";
    private static final String KEY_JSON = "json";

 
    private static final String[] COLUMNS = {KEY_ID, KEY_JSON};
 
    public void saveClient(Client client){
        Log.d("addClient", client.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
//        values.put(KEY_ID, client.getId());  
        values.put(KEY_JSON, client.toJSON());   
 
        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
        // 4. close
        db.close(); 
    }
 
    public Client queryClient(int id){
 
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
 
        // 4. build client object
        Client client = new Client();
        client = Client.setJSON(cursor.getString(1));
        client.setId(cursor.getInt(0));
        
        Log.d("getClient("+id+")", client.toString());
 
        // 5. return client
        return client;
    }
 
    // Get All Clients
    public List<Client> queryAllClients() {
        List<Client> clients = new LinkedList<Client>();
 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;
 
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build client and add it to list
        Client client = null;
        if (cursor.moveToFirst()) {
            do {
                client = Client.setJSON(cursor.getString(1));
                client.setId(cursor.getInt(0));
                // Add client to clients
                clients.add(client);
            } while (cursor.moveToNext());
        }
 
        Log.d("getAllClients()", clients.toString());
 
        // return clients
        return clients;
    }
 
     // Updating single client
    public int updateClient(Client client) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("json", client.toJSON()); // get title 
     
        // 3. updating row
        int i = db.update(TABLE_NAME, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(client.getId()) }); //selection args
 
        // 4. close
        db.close();
 
        return i;
 
    }
 
    // Deleting single client
    public void deleteClient(Client client) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_NAME,
                KEY_ID+" = ?",
                new String[] { String.valueOf(client.getId()) });
 
        // 3. close
        db.close();
 
        Log.d("deleteClient", client.toString());
 
    }
    
    // Deleting all clients
    public void deleteAllClients() {
 
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