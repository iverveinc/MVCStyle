package com.mvcstyle.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mvcstyle.R;

/**
 * Created by Android on 5/25/2016.
 */
public class MapActivity extends FragmentActivity implements View.OnClickListener {

    double latitude = 0;
    double longitude = 0;
    private GoogleMap map;
    String addressName;
    String UserName;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.sel_text));
        }

        getId();
        setListeners();

        latitude = getIntent().getDoubleExtra("latitude", latitude);
        Log.e("++++++","=========" +latitude);
        // Receiving longitude from MainActivity screen
        longitude = getIntent().getDoubleExtra("longitude", longitude);

        addressName = getIntent().getExtras().getString("addressName", addressName);
        UserName  = getIntent().getExtras().getString("UserName", UserName);
        Log.e("++++++","=========" +longitude);

        LatLng position = new LatLng(latitude, longitude);

        // Instantiating MarkerOptions class
        MarkerOptions options = new MarkerOptions();

        // Setting position for the MarkerOptions
        options.position(position);

        // Setting title for the MarkerOptions
        options.title(UserName);

        // Setting snippet for the MarkerOptions
        options.snippet(addressName);
//        options.snippet("Latitude:" + latitude + ",Longitude:" + longitude);

        // Getting Reference to SupportMapFragment of activity_map.xml
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();;

        map.addMarker(options);

        // Creating CameraUpdate object for position
        CameraUpdate updatePosition = CameraUpdateFactory.newLatLng(position);

        // Creating CameraUpdate object for zoom
        CameraUpdate updateZoom = CameraUpdateFactory.zoomBy(4);

        // Updating the camera position to the user input latitude and longitude

        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setTrafficEnabled(true);

        map.getUiSettings().setAllGesturesEnabled(true);

        LatLng ll = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions()
                .position(ll)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Log.e("AGAIN ", "dasdasd");
        //LatLng lat = new LatLng(Double.parseDouble(SessionManager.getlattitude(getApplicationContext())),Double.parseDouble(SessionManager.getlongitude(getBaseContext())));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 24));
        map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

    }

    private void setListeners() {
        ivBack.setOnClickListener(this);
    }

    private void getId() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.ivBack:
                Intent intentBack = new Intent(MapActivity.this,ProfileActivity.class);
                startActivity(intentBack);
                break;
        }

    }
}
