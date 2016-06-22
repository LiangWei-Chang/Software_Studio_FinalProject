package com.software.studio.delicacies;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.software.studio.delicacies.fragments.DirectionsJSONParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener {

    GoogleMap mMap;
    Button button, button2, navi;
    LatLng current;
    LatLng position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        button = (Button)this.findViewById(R.id.map_current);
        button.setOnClickListener(this);
        button2 = (Button)this.findViewById(R.id.map_position);
        button2.setOnClickListener(this);
        navi = (Button)this.findViewById(R.id.map_navigate);
        navi.setOnClickListener(this);
        mapFragment.getMapAsync(this);
    }

     public void onClick(View v)
     {
         if(v==button)
         {
             CameraPosition cameraPosition = new CameraPosition.Builder().target(current).zoom(15).build();
             mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
         }else if(v==button2)
         {
             CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(15).build();
             mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
         }else if(v==navi)
         {
             String url = getDirectionsUrl(current, position);

             DownloadTask downloadTask = new DownloadTask();

             downloadTask.execute(url);
         }
     }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = this.getIntent();
        mMap = googleMap;
        Location location=null;
        final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;

        // The minimum time beetwen updates in milliseconds
        final long MIN_TIME_BW_UPDATES = 0;
        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;
        String locationName = intent.getStringExtra("LocationName");
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("error","location manager error");
            return;
        }
        isGPSEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
            Toast.makeText(getApplicationContext(),"GPS or Network disable",Toast.LENGTH_SHORT).show();
        } else {
            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                Log.d("GPS Enabled", "GPS Enabled");
                if (manager != null) {
                    location = manager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    //updateGPSCoordinates();
                }
            }
            // First get location from Network Provider
            if (isNetworkEnabled) {
                    Log.d("Network", "Network");

                    if (manager != null) {
                        location = manager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        //updateGPSCoordinates();
                    }
                }


        }
        if(location!=null) {
            current = new LatLng(location.getLatitude(), location.getLongitude());
            // Log.d("position",(new Double(location.getLatitude()).toString())+"--"+(new Double(location.getLongitude()).toString()));
            mMap.addMarker(new MarkerOptions().position(current).title("Here").snippet("Current place"));
        }
        LocationNameToMarker(locationName);

    }

    private void LocationNameToMarker(String locationName){
        Geocoder geocoder = new Geocoder(getBaseContext());
        List<Address> addressList = null;
        int maxResult = 1;
        try {
            // 翻譯地址可能回傳多種結果，所以回傳 List
            // maxResult = 1 最多回傳一筆
            addressList = geocoder.getFromLocationName(locationName, maxResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(addressList == null || addressList.isEmpty()) {
            Toast.makeText(getBaseContext(), R.string.msg_LocationNameNotFound, Toast.LENGTH_SHORT).show();
        }
        else {
            // 因為只有一筆資料
            Address address = addressList.get(0);

            // 取得經緯度
            position = new LatLng(address.getLatitude(), address.getLongitude());

            // 將地址當作標記的描述文字
            String snippet = address.getAddressLine(0);

            // 將地址在地圖上標紀
            MarkerOptions options = new MarkerOptions().position(position).title(locationName).snippet(snippet);
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(options);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(15).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception download url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.BLUE);
            }

            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

}
