package com.software.studio.delicacies;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        String locationName = intent.getStringExtra("LocationName");
        LocationNameToMarker(locationName);
    }

    private void LocationNameToMarker(String locationName){
        mMap.clear();
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
            LatLng position = new LatLng(address.getLatitude(), address.getLongitude());

            // 將地址當作標記的描述文字
            String snippet = address.getAddressLine(0);

            // 將地址在地圖上標紀
            mMap.addMarker(new MarkerOptions().position(position).title(locationName).snippet(snippet));

            CameraPosition cameraPosition = new CameraPosition.Builder().target(position).zoom(15).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

}
