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

public class locdata extends ActionBarActivity implements OnClickListener {
	Locations ls;
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		 setContentView(R.layout.locations);
		 Button b1=(Button) findViewById(R.id.button1);
		 b1.setOnClickListener(this);	
}
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
			getAll();
		
	}

	private void getAll() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_display);
		 
		String res;
		ls = new Locations(getApplicationContext());
		ls.open();
		res=ls.getData();
		ls.close();
		TextView tv=(TextView) findViewById(R.id.textView1);
		tv.setText(res);
	}
}
