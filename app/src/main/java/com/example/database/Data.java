package com.example.database;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class Data extends BroadcastReceiver
{    
	
	Locations lc;
	String Tel;
	private static final String TAG ="Message received Activity";
	@Override
	public void onReceive(Context context, Intent intent) 
	{        
		//---get the SMS message that was received---
		Log.d(TAG,"Message Received");
		Bundle bundle = intent.getExtras();        
		SmsMessage[] msgs = null;
		String str="";            
		if (bundle != null)
		{
			Tel = "";

			//---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i=0; i<msgs.length; i++){
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				if (i==0) {
					//---get the sender address/phone number---
					Tel = msgs[i].getOriginatingAddress();
				} 
				//---get the message body---
				str += msgs[i].getMessageBody().toString();                	
			}
/* Checks if User is tracker*/
			if (str.startsWith("H:")) {   
			String[] parts = str.split(":");
                String ms=parts[1];
                parts = ms.split(",");
                String lat=parts[0];
                String lon=parts[1];
                lc = new Locations(context);
                lc.open();
                lc.insert(Tel,lat,lon);
                lc.close();
                trackers tr = new trackers(context);
                Shortest st=new Shortest();
                double lati,loni;
                Runner rn=new Runner();
                lati=Double.parseDouble(lat);
                loni=Double.parseDouble(lon);
               if(rn.coordinate_is_inside_polygon(lati,loni)){
              	 tr.open();
                   String[][] i;
                   i=tr.track(lati,loni,Tel);
                    tr.close();
                    if(i[0][0] != ""){ 
                    	String res=st.cali(lati, loni, i);
                    	String inp[]=res.split(",");  
                    	trackers tri=new trackers(context);
                    	tri.open();
                    	tri.modify(inp[0],inp[2]);
                    	tri.add(Tel,inp[1]);
                    	tri.close();
                    	SmsManager smsManager = SmsManager.getDefault();
                    	String sm="T!"+inp[2]+":"+Tel+"@"+lati+","+loni;
                    	smsManager.sendTextMessage(inp[0], null,sm, null, null);
                    	Intent in=new Intent(context,track.class);	
                    	in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    	in.putExtra("message",sm);
                    	context.startActivity(in);
    				}
                    else{
                    	Intent in=new Intent(context,track.class);	
                    	in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    	in.putExtra("message","No Trackers");
                    	context.startActivity(in);
                    }
               }
               else{
                	Intent in=new Intent(context,track.class);	
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	in.putExtra("message","Not in Vadlamudi"); 
    				context.startActivity(in);
    				this.abortBroadcast();
                }
               
             		}
			  if(str.startsWith("S:")){
           	   String sm[]=str.split(":");
           	   String val=sm[1];
           	   trackers tri = new trackers(context);
           	   	tri.open();
           	   	tri.change(val);
           	   	tri.close();
                this.abortBroadcast();
			  }
    
				
			}
		}                         
}



