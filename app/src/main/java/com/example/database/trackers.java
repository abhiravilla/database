package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class trackers  {
	public static final String key_row="Number";
	public static final String key_row1="Latitude";	
	public static final String key_row2="Longitude";
	public static final String key_row3="InWork";
	public static final String key_row4="User";
	public static final String key_row5="Value";
	
	public static final String Database_name ="trackersdata";
	private static final String Table="trackers";
	private static final int Database_version=1;
	
	private DbHelper newuser;
	private final Context nucontext;
	private SQLiteDatabase userdatabase;
	int nRow,mRow,pRow,dRow,qRow,rRow;
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, Database_name,null,Database_version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE `trackers` (`Number`	TEXT ,`Time`	TEXT,`Latitude`	TEXT ,`Longitude`	TEXT , `InWork` TEXT DEFAULT `0`, `User` TEXT DEFAULT `000`, `Value` TEXT PRIMARY KEY);");
			}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS user");
			onCreate(db);
		}		
	}
	
	public trackers(Context c){
		nucontext = c;
	}
	public trackers open() throws SQLException{
		newuser = new DbHelper(nucontext);
		userdatabase =newuser.getWritableDatabase();
		return this;
	}
	
	public void close(){
		newuser.close();
	}
	public long insert(String tel, String lat,String lon,String val) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		cv.put(key_row,tel);
		cv.put(key_row1,lat);
		cv.put(key_row2,lon);
		cv.put(key_row5,val);
		long re= userdatabase.insert(Table, null, cv);
		if(re==-1)
			Toast.makeText(nucontext, "Error Occured: Might be duplicate data", Toast.LENGTH_SHORT).show();
		return 1;
	}
	public String getData(){
		String[] columns = new String[]{key_row,key_row1,key_row2,key_row3,key_row4,key_row5};
		Cursor c = userdatabase.query(Table, columns, null,null, null, null, null);
		String result="";
		 nRow=c.getColumnIndex(key_row);
		 mRow=c.getColumnIndex(key_row1);
	     dRow=c.getColumnIndex(key_row2);
		 pRow=c.getColumnIndex(key_row3);
		 qRow=c.getColumnIndex(key_row4);
		 rRow=c.getColumnIndex(key_row5);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
result=result+c.getString(nRow)+"\t\t"+c.getString(mRow)+"\t\t"+c.getString(dRow)+"\t\t"+c.getString(pRow)+"\t\t"+c.getString(qRow)+"\t\t"+c.getString(rRow)+"\n";
		}
		return result;
	}
	public void deleteall(){
		userdatabase.execSQL("delete from "+ Table);
	}
public String[][] track( double lat, double lon, String Number) {
		
		double value1=0.004712,value2=0.004499;
		double latmi,latma,longmi,longma;
		String longmin,longmax,latmin,latmax;
		String[][] result=new String[][]{};
		int i=1;
		do{
	    latmi=lat-value1*i;
		latma=lat+value1*i;
		longmi=lon-value2*i;
		longma=lon+value2*i;
		longmin=String.valueOf(longmi);
		longmax=String.valueOf(longma);
		latmin=String.valueOf(latmi);
		latmax=String.valueOf(latma);
		String work="1";
		Cursor c = userdatabase.query(Table,null," User == ? ",new String[]{Number},null,null,null,null);
		if(c.getCount()>0){
		     result=new String[4][1];
			 nRow=c.getColumnIndex(key_row);
			 mRow=c.getColumnIndex(key_row1);
			 dRow=c.getColumnIndex(key_row2);
			 pRow=c.getColumnIndex(key_row5);
			c.moveToFirst();
		     	result[0][0]=c.getString(nRow);
				result[1][0]=c.getString(mRow);
				result[2][0]=c.getString(dRow);
				result[3][0]=c.getString(pRow);							
		}
		else{
			c = userdatabase.query(Table,null,"(Latitude>=?"+" and "+"Latitude<=?)"+" and "+"(Longitude>=?"+" and "+"Longitude<=?)"+" and "+"InWork<>?",new String[]{latmin,latmax,longmin,longmax,work},null,null,null,null);
		 nRow=c.getColumnIndex(key_row);
		 mRow=c.getColumnIndex(key_row1);
		 dRow=c.getColumnIndex(key_row2);
		 pRow=c.getColumnIndex(key_row5);		
		 int cnt=c.getCount();
		if(c.getCount()>0){
		result=new String[4][cnt];
	    int  j=0;
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
		 result[0][j]=c.getString(nRow);
		 result[1][j]=c.getString(mRow);
		 result[2][j]=c.getString(dRow);
		 result[3][j]=c.getString(pRow);
		 j++;
		}
		
		}
		else{
			result=new String[][]{{"",""}};
			
		}

		}
		i++;
		Log.d("dcd",""+i);
		}while(i<6 && result[0][0]=="");
		return result;
	}

public void modify(String Number,String val){
	userdatabase.execSQL("UPDATE trackers SET InWork='1' WHERE Number='"+Number+"' AND Value='"+val+"'");
	}
public void add(String Number, String lat){
	userdatabase.execSQL("UPDATE trackers SET User='"+Number+"' WHERE Latitude='"+lat+"'");
}
public void change(String val){
	Log.d("asd","sda");
	userdatabase.execSQL("UPDATE trackers SET InWork='0' WHERE Value='"+val+"'");
	userdatabase.execSQL("UPDATE trackers SET User='000' WHERE Value='"+val+"'");

}
public int remove(String val){
	//int i=userdatabase.delete(Table, "Value == '"+val+"'",null);
	ContentValues cv=new ContentValues();
	cv.put(key_row,val);
	int i=userdatabase.update(Table,cv,null,null);
	return i;
}
}