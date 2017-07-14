package com.example.donghyunlee.project2w;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by DONGHYUNLEE on 2017-07-13.
 */

public class AddMap extends Fragment implements OnMapReadyCallback {
    public AddMap() {
        Log.e("AddMap 생성자", "들어옴");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //ImageButton mapback = (ImageButton)
        Log.e("onCreateView ", "들어옴");
        View v = inflater.inflate(R.layout.addmap, container, false);
        MapView mapview=(MapView)v.findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
        mapview.onResume();
        mapview.getMapAsync(this);
        Log.e("onCreateView return 전", "들어옴");



        return v;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        Log.e("onMapReady ", "들어옴");
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }


}
