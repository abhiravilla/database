package com.example.database;
import java.util.ArrayList;

public class Runner
{
    public static double PI = 3.14159265;
    public static double TWOPI = 2*PI;
    public static void main(String[] args) {
    
    
}
public static boolean coordinate_is_inside_polygon(
    double latitude, double longitude)
{       
       int i;
       ArrayList<Double> lat_array = new ArrayList<Double>();
        ArrayList<Double> long_array = new ArrayList<Double>();

       ArrayList<String> polygon_lat_long_pairs = new ArrayList<String>();
       polygon_lat_long_pairs.add("16.235029, 80.544934");
       polygon_lat_long_pairs.add("16.236101, 80.546393");
       polygon_lat_long_pairs.add("16.236966, 80.547810");
       polygon_lat_long_pairs.add("16.237996, 80.549140");
       polygon_lat_long_pairs.add("16.238079, 80.549526");
       polygon_lat_long_pairs.add("16.239068, 80.550985");
       polygon_lat_long_pairs.add("16.239892, 80.552144");
       polygon_lat_long_pairs.add("16.240798, 80.553517");
       polygon_lat_long_pairs.add("16.241746, 80.555062");
       polygon_lat_long_pairs.add("16.242570, 80.556264");
       polygon_lat_long_pairs.add("16.242652, 80.557809");
       polygon_lat_long_pairs.add("16.242570, 80.559225");
       polygon_lat_long_pairs.add("16.242570, 80.561156");
       polygon_lat_long_pairs.add("16.242652, 80.562873");
       polygon_lat_long_pairs.add("16.242652, 80.564461");
       polygon_lat_long_pairs.add("16.242611, 80.566306");
       polygon_lat_long_pairs.add("16.241952, 80.566564");
       polygon_lat_long_pairs.add("16.240551, 80.567036");
       polygon_lat_long_pairs.add("16.239109, 80.567508");
       polygon_lat_long_pairs.add("16.237914, 80.568023");
       polygon_lat_long_pairs.add("16.236472, 80.568409");
       polygon_lat_long_pairs.add("16.234906, 80.567937");
       polygon_lat_long_pairs.add("16.233505, 80.567465");
       polygon_lat_long_pairs.add("16.231940, 80.567036");
       polygon_lat_long_pairs.add("16.230415, 80.566435");
       polygon_lat_long_pairs.add("16.228932, 80.565963");
       polygon_lat_long_pairs.add("16.227531, 80.565019");
       polygon_lat_long_pairs.add("16.226212, 80.564160");
       polygon_lat_long_pairs.add("16.224893, 80.563130");
       polygon_lat_long_pairs.add("16.223328, 80.561972");
       polygon_lat_long_pairs.add("16.221968, 80.560899");
       polygon_lat_long_pairs.add("16.220938, 80.560212");
       polygon_lat_long_pairs.add("16.221391, 80.558152");
       polygon_lat_long_pairs.add("16.221844, 80.555921");
       polygon_lat_long_pairs.add("16.222174, 80.554419");
       polygon_lat_long_pairs.add("16.222627, 80.552101");
       polygon_lat_long_pairs.add("16.222998, 80.550642");
       polygon_lat_long_pairs.add("16.223534, 80.549355");
       polygon_lat_long_pairs.add("16.223534, 80.549355");
       polygon_lat_long_pairs.add("16.225800, 80.547724");
       polygon_lat_long_pairs.add("16.227366, 80.546608");
       polygon_lat_long_pairs.add("16.228602, 80.545621");
       polygon_lat_long_pairs.add("16.230003, 80.544720");
       polygon_lat_long_pairs.add("16.231157, 80.544548");
       polygon_lat_long_pairs.add("16.232228, 80.544677");
       polygon_lat_long_pairs.add("16.233753, 80.544848");
       polygon_lat_long_pairs.add("16.234535, 80.544848");


       //Convert the strings to doubles.       
       for(String s : polygon_lat_long_pairs){
           lat_array.add(Double.parseDouble(s.split(",")[0]));
           long_array.add(Double.parseDouble(s.split(",")[1]));
       }
       double angle=0;
       double point1_lat;
       double point1_long;
       double point2_lat;
       double point2_long;
       int n = lat_array.size();

       for (i=0;i<n;i++) {
          point1_lat = lat_array.get(i) - latitude;
          point1_long = long_array.get(i) - longitude;
          point2_lat = lat_array.get((i+1)%n) - latitude; 
          //you should have paid more attention in high school geometry.
          point2_long = long_array.get((i+1)%n) - longitude;
          angle += Angle2D(point1_lat,point1_long,point2_lat,point2_long);
       }

       if (Math.abs(angle) < PI){
          return false;
       }
       else{
    	  
    	   
           return true;
       }
}

public static double Angle2D(double y1, double x1, double y2, double x2)
{
   double dtheta,theta1,theta2;

   theta1 = Math.atan2(y1,x1);
   theta2 = Math.atan2(y2,x2);
   dtheta = theta2 - theta1;
   while (dtheta > PI)
      dtheta -= TWOPI;
   while (dtheta < -PI)
      dtheta += TWOPI;

   return(dtheta);
}

public static boolean is_valid_gps_coordinate(double latitude, 
    double longitude)
{
    //This is a bonus function, it's unused, to reject invalid lat/longs.
    if (latitude > -90 && latitude < 90 && 
            longitude > -180 && longitude < 180)
    {
        return true;
    }
    return false;
}
}
