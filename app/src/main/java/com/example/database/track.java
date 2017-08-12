package com.example.database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class track extends ActionBarActivity  {
	trackers ls;
	String lati,loni;
	EditText et1,et2;
	TextView tv;
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		 setContentView(R.layout.view);
		 tv=(TextView) findViewById(R.id.textView1);
		 et1=(EditText) findViewById(R.id.editText1);
		 et2=(EditText) findViewById(R.id.editText2);
		 Button b1=(Button) findViewById(R.id.button1);
		 b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tracking();
			}
		});
		 Intent intent=getIntent();
		 String res=intent.getStringExtra("message");
		 tv.setText(res);
	}
 
public void	tracking(){
	trackers tr=new trackers(getApplicationContext());
     lati=et1.getText().toString();
     loni=et2.getText().toString();
     double lat,lon;
     Runner rn=new Runner();
     lat=Double.parseDouble(lati);
     lon=Double.parseDouble(loni);
     if(rn.coordinate_is_inside_polygon(lat,lon)){
    	 tv.setText("In vadlamudi ");
     }
     else{
    	 tv.setText("Not in Vadlamudi");
     }
   
     
}
}

