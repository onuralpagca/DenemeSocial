package com.alp.denemesocial;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class UserLocation extends Activity {

    public static double kullaniciLatitude;
    public static double kullaniciLongitude;

//Hocam uygulamayı yükledikten sonra konumu değiştirip tekrar yakındaki yerleri ara butonuna basınca uygulama çöküyor.

    Context mContext; //Hocam bu contexti internetten araştırıp koydum. MapsActivity dışında nasıl konum alınır diye araştırıp buldum.
    public UserLocation(Context mContext) {

        this.mContext = mContext;
    }


    LocationManager locationManager;
    LocationListener locationListener;

    public void konumAl(){


        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {

              kullaniciLatitude = location.getLatitude();
              kullaniciLongitude = location.getLongitude();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){                                                                                                  //EĞER ZATEN ÖNCEDEN İZİN VERİLDİYSE
            ActivityCompat.requestPermissions((Activity) mContext ,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {                //KULLANICI İZİNLERİ VEREDİĞİNDE OLACAKLAR
        if(grantResults.length>0){          //0 DAN BÜYÜKSE DEMEK, YANİ BİR SONUÇ GELDİYSE ONDAN SONRA İŞLEM YAP
            if(requestCode==1){                // YUKARDA REQUEST CODE 1 VERMİŞTİK. YİNE 1 Mİ GELDİ DİYE KONTROL EDİYORUZ.
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){            //İZNİ VERMİŞ Mİ KONTROL EDİYORUZ. YUKARIDAKİ KODUN == HALİ
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);                          //İZNİ VERİRSE KULLANICININ YERİNİ AL


                }

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }




}
