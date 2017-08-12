package com.example.database;
import java.lang.Math;

import android.util.Log;
class create{
public double distance(double lat1, double lon1, double lat2, double lon2) {
      double theta = lon1 - lon2;
      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
      dist = Math.acos(dist);
      dist = rad2deg(dist);
      dist = dist * 60 * 1.1515;
       return (dist);
    }

   public double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }
   public double rad2deg(double rad) {
      return (rad * 180.0 / Math.PI);
    }

}
class Shortest {
public static void main(String args[]){
}
 String cali(double lat, double lon,String loc[][]){
	double value=999999.99;
        double earthradius=3958.75;
        int rownum=-1;
        create cr=new create();
        int cnt=loc[0].length;
        for(int i=0;i<cnt;i++){
        	double lati,longi;
        	lati=Double.parseDouble(loc[1][i]);
        	longi=Double.parseDouble(loc[2][i]);
            double cu=cr.distance(lat,lon,lati,longi);
                if(cu<value){
                rownum=i;
                value=cu;
            }
        }
        return (""+loc[0][rownum]+","+loc[1][rownum]+","+loc[3][rownum]);
}
}
