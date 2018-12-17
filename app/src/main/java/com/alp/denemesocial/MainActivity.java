package com.alp.denemesocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    double lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserLocation userLocation = new UserLocation(this);
        userLocation.konumAl();




    }

    public void mekanlar(View view){

        UserLocation userLocation = new UserLocation(this);
        userLocation.konumAl();

        lat = userLocation.kullaniciLatitude;
        lon = userLocation.kullaniciLongitude;


        System.out.println("Kullanıcının yeri: " + lat + "," + lon);


        DataDownload dataDownload = new DataDownload();



        try {
            String url = "https://developers.zomato.com/api/v2.1/geocode?lat="+lat+"&lon="+lon+"&apikey=480b8c7fef8c2f96bfc82ba397b84e88";

            //String url = "https://developers.zomato.com/api/v2.1/geocode?lat=39.985548&lon=32.696529&apikey=480b8c7fef8c2f96bfc82ba397b84e88";
            dataDownload.execute(url);
        }catch (Exception e){

        }

            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);

    }





}
