package org.maatanyy.janggon.hanra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MapActivity extends AppCompatActivity implements AutoPermissionsListener {

    SupportMapFragment mapFragment;  //Supportmapfragment 안에 들어있는 구글맵 객체 위에 지도가 표시
    GoogleMap map;
    MarkerOptions myLocationMarker;

    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map=googleMap;
                map.setMyLocationEnabled(true);
            }
        });

        try{
            MapsInitializer.initialize(this);
        }catch (Exception e){
            e.printStackTrace();
        }

        textView5 = findViewById(R.id.textView5);

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }



    public void startLocationService(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(location !=null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude :" + longitude;

                textView5.setText(message);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 5000;   //최소 시간으로 5초 마다
            float minDistance = 0;  //최소 거리 0

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
            Toast.makeText(getApplicationContext(),"내 위치확인 요청함",Toast.LENGTH_SHORT).show();

        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    @Override   //위험 권한 라이브러리 이용해서 추가했음 책 pg 382 참고!!
    public void onDenied(int requestCode, String[] permissions) {
        Toast.makeText(this,"permissions denied : " +permissions.length,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        Toast.makeText(this,"permissions granted : " +permissions.length,Toast.LENGTH_LONG).show();
    }

    class GPSListener implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String message = "내 위치-> Latitude : " + latitude + "\nLongitude:" + longitude;
            textView5.setText(message);
            showCurrentLocation(latitude,longitude);
        }
    }

    private void showCurrentLocation(Double latitude,Double longitude){
        LatLng curPoint = new LatLng(latitude,longitude);    //현재 위치 좌표로 LatLng 객체 생성
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,11));   // 지정한 위치의 지도 보여주기기, 숫자 커기면 더 크게 보임 보통 19나 21이 최대
        showMyLocationMarker(curPoint);
   }

   public void onResume(){
        super.onResume();

        if(map!=null){
            map.setMyLocationEnabled(true);
        }
   }

   public void onPause(){
        super.onPause();

        if(map!=null){
            map.setMyLocationEnabled(false);
        }
   }

   private void showMyLocationMarker(LatLng curPoint){
        if(myLocationMarker==null){
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("내 위치\n");
            myLocationMarker.snippet("GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.flag));
            map.addMarker(myLocationMarker);
        }else{
            myLocationMarker.position(curPoint);
        }
   }

}