package com.devivekumar.databaserough;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people";
    private static final String COL1 = "id";
    private static final String COL2 = "name";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String openDatabase = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY, " + COL2 + " TEXT)";
        db.execSQL(openDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }
    public  boolean addData(String data){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL2,data);
        Log.d(TAG, "addData: Data is being added");
        long result = db.insert(TABLE_NAME,null,contentValues);
        Log.d(TAG, "addData: result "+result);
        return result==-1?false:true;
    }
    public Cursor getData(){
        SQLiteDatabase db= getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;
    }
    public void updateName(String newName,String oldName){
        SQLiteDatabase db=getWritableDatabase();
        String query="UPDATE "+ TABLE_NAME+" SET "+ COL2+" = '"+newName+"' WHERE "+COL2+" = '"+oldName+"'";
        Log.d(TAG, "updateName: query " +query);
        db.execSQL(query);
    }
    public void deleteName(String oldName){
        SQLiteDatabase db=getWritableDatabase();
        String query="DELETE FROM "+ TABLE_NAME+" WHERE "+COL2+" = '"+oldName+"'";
        Log.d(TAG, "updateName: query " +query);
        db.execSQL(query);
    }
}
