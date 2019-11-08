package com.example.awarenessadmin.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.awarenessadmin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_nav_map);

        getSupportActionBar().setTitle("လတ်တလောဘေးအန္တရာယ်");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(21.9162, 95.9560))
                .zoom((float) 5.5)
                .bearing(0)
                .tilt(45)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(16.853986, 96.1349873))
                .title("Yangon")
                .snippet("ရေကြီး")
                .snippet("yay taw taw kyi nay pr p")
                .rotation((float) 0.0)
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.flood_icon)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(21.97, 96.08))
                .title("Mandalay")
                .snippet("ငလျှင်လှုပ်")
                .snippet("equark tway hloke nay pr p,lklgsfgo")
                .rotation((float) 0.0)
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.earthquake_icon_small)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(19.745, 96.12972))
                .title("NayPyiDaw")
                .snippet("လေဆင်နှာမောင်း")
                .rotation((float) 0.0)
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.tornado_small)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(20.14624, 92.89835))
                .title("Sittwe")
                .snippet("ဆိုင်ကလုန်း")
                .rotation((float) 0.0)
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.cyclone_icon)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(16.91867, 97.37001))
                .title("Thathone")
                .snippet("ဆူနာမီ")
                .rotation((float) 0.0)
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.earthquake_icon_small)));


    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


}
