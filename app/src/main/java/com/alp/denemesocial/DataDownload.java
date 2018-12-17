package com.alp.denemesocial;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class DataDownload extends AsyncTask<String, Void, String> {

    //Hocam hemen aşağı satırdaki latitudeArray ve longitudeArray'deki verileri MapsActivity'e aktarmaya çalışıyorum. Ama başaramadım.

 public static ArrayList<Double> latitudeArray = new ArrayList<Double>();
 public static ArrayList<Double> longitudeArray = new ArrayList<Double>();




    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        URL url;
        HttpsURLConnection httpsURLConnection;

        //Bunlar ezber;
        try{
            url = new URL(strings[0]);
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = httpsURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while(data>0){

                char character = (char) data;  //JSONDaki verileri Karakter karakter okuyoruz ve bir alt satırda da result stringine ekleye ekleye aktarıyoruz.
                result += character;

                data = inputStreamReader.read();

            }


            return result;

        }catch(Exception e){
            return null;
        }



    }
    //aşağıdaki method için Yukarıdaki sekmelerden code--> Override methods onpostexecute
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //System.out.println("alınan data: " + s);



        try{
            JSONObject json = new JSONObject(s);
            JSONArray jsonArray = json.getJSONArray("nearby_restaurants");

            for(int i=0; i<jsonArray.length(); i++ ){
                JSONObject object = jsonArray.getJSONObject(i);
                String restaurant = object.getString("restaurant");

                JSONObject jsonObject0 = new JSONObject(restaurant);
                String id = jsonObject0.getString("id");

                System.out.println("onuralp: " + id);

                JSONObject jsonObject1 = new JSONObject(restaurant);
                String location = jsonObject1.getString("location");

                JSONObject jsonObject2 = new JSONObject(location);
                String latitude = jsonObject2.getString("latitude");
                //System.out.println("Enlem: " + latitude);
                latitudeArray.add(Double.parseDouble(latitude));


                String longitude = jsonObject2.getString("longitude");
                longitudeArray.add(Double.parseDouble(longitude));

            }


            //System.out.println("Boylam: " + longitudeArray);
            //System.out.println("Boylam dördüncü: " + longitudeArray.get(3));

        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }
}