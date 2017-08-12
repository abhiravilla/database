package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;


public class Locations  {
	public static final String key_row="Number";
	public static final String key_row3="Latitude";
	public static final String key_row4="Longitude";
	public static final String Database_name ="locationdata";
	private static final String Table="locations";
	private static final int Database_version=1;
		private DbHelper Locationuser;
	private final Context locontext;
	private SQLiteDatabase locdatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, Database_name,null,Database_version);
			// TODO Auto-generated constructor stub
		}
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE `locations` (`Number`	TEXT ,`Latitude`	TEXT ,`Longitude`	TEXT );");
			}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS operations");
			onCreate(db);
		}
		
	}
	
	public Locations(Context c){
		locontext = c;
	}
	//Opens the database and uses it
	public Locations open() throws SQLException{
		Locationuser = new DbHelper(locontext);
		locdatabase =Locationuser.getWritableDatabase();
		return this;
	}
	// Closes the database
	public void close(){
		Locationuser.close();
	}
	// Insert the data into the table
	public String getData(){
		String[] columns = new String[]{key_row,key_row3,key_row4};
		Cursor c = locdatabase.query("locations", columns, null,null, null, null, null);
		String result="";
		int nRow=c.getColumnIndex(key_row);
		int laRow=c.getColumnIndex(key_row3);
		int loRow=c.getColumnIndex(key_row4);
		result="NUMBER\t\t\t"+"LATITUDE\t\t\t"+"LONGITUDE\n";
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result=result+c.getString(nRow)+"\t\t"+c.getString(laRow)+"\t\t"+c.getString(loRow)+ "\n";
		}
		return result;
	}
	// Deletes the entire table contents	
	public void deleteall(){
		locdatabase.execSQL("delete from "+ Table);
	}
	public long insert(String tel, String lat, String lon) {
		// TODO Auto-generated method stub
		Log.d("LOCATIONS TABLE","Inserting values");
		ContentValues cv=new ContentValues();
		cv.put(key_row,tel);
		cv.put(key_row3, lat);
		cv.put(key_row4, lon);
		return locdatabase.insert(Table, null, cv);
}
}
