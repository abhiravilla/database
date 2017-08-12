package com.example.database;

import android.content.Intent;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class trackdata extends ActionBarActivity implements OnClickListener {
	trackers ts;
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 setContentView(R.layout.trackers);
		 Button b1=(Button) findViewById(R.id.button1);
		 b1.setOnClickListener(this);	
		 Button b2=(Button) findViewById(R.id.button2);
		 b2.setOnClickListener(this);	
		 Button b3=(Button) findViewById(R.id.button3);
		 b3.setOnClickListener(this);	
		 Button b4=(Button) findViewById(R.id.button4);
		 b4.setOnClickListener(this);	
	
	}
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch(v.getId()){	
	case R.id.button1:getAll();
	break;
	case R.id.button2:save();
	break;
	case R.id.button3:search();
	break;
	case R.id.button4:delete();
	
	}
		
	}

	private void delete() {
		EditText et4=(EditText) findViewById(R.id.editText5);
		String val=et4.getText().toString();
	 ts=new trackers(getApplicationContext());
	 ts.open();
	 if(ts.remove(val)== 0){
		Toast.makeText(getApplicationContext(), "Zero rows Deleted", Toast.LENGTH_SHORT).show();
		ts.close();
	 }
	 else{
		 Toast.makeText(getApplicationContext(), " Row Deleted", Toast.LENGTH_SHORT).show();
	    ts.close();
	 }
	}
	private void save() {
	// TODO Auto-generated method stub
		EditText et1,et2,et3,et4;
		String tel,lat,lon;
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
		et3=(EditText) findViewById(R.id.editText3);
		et4=(EditText) findViewById(R.id.editText4);

		tel=et1.getText().toString();
		lat=et2.getText().toString();
		lon=et3.getText().toString();
		String val=et4.getText().toString();
		
		if(tel.matches("") || lat.matches("")|| lon.matches("") || val.matches(""))
			Toast.makeText(getApplicationContext(), "Enter Values in all fields", Toast.LENGTH_SHORT).show();
	
		else{
			ts= new trackers(getApplicationContext());
			ts.open();
			ts.insert(tel, lat, lon,val);
			ts.close();
			}
}
	private void search(){
		Intent 	ld = new Intent(this,track.class);
      	startActivity(ld);
	}
	private void getAll() {
		// TODO Auto-generated method stub
		String res;
		ts= new trackers(getApplicationContext());
		ts.open();
		res=ts.getData();
		ts.close();
		setContentView(R.layout.activity_display);
		TextView tv=(TextView) findViewById(R.id.textView1);
		tv.setText(res);
	}
}
