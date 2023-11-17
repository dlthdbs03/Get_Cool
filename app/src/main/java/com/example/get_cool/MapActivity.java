package com.example.get_cool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.poi_item.TMapPOIItem;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    private static final String TAG = "MapActivity";
    private static final int YOUR_REQUEST_CODE = 1;
    private static final int YOUR_GPS_ENABLE_REQUEST_CODE = 2;

    private TMapView tMapView;
    private LinearLayout linearLayoutTmap;
    private TMapGpsManager tmapgps = null;

    Bread bread;
    Grocery grocery;
    Side side;
    Cafe cafe;
    Morning morning;
    Korean korean;
    Japanese japanese;
    Chinese chinese;
    Western western;
    Evening evening;

    ImageView bread_button;
    ImageView grocery_button;
    ImageView side_button;
    ImageView cafe_button;
    ImageView morning_button;
    ImageView korean_button;
    ImageView japanese_button;
    ImageView chinese_button;
    ImageView western_button;
    ImageView evening_button;
    Button buttonZoomIn;
    Button buttonZoomOut;
    Button buttoncurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        bread_button = findViewById(R.id.bread);
        grocery_button = findViewById(R.id.grocery);
        side_button = findViewById(R.id.side);
        cafe_button = findViewById(R.id.cafe);
        morning_button = findViewById(R.id.morning);
        korean_button = findViewById(R.id.korean);
        japanese_button = findViewById(R.id.japanese);
        chinese_button = findViewById(R.id.chinese);
        western_button = findViewById(R.id.western);
        evening_button = findViewById(R.id.evening);
        buttonZoomIn = findViewById(R.id.buttonZoomIn);
        buttonZoomOut = findViewById(R.id.buttonZoomOut);
        buttoncurrent = findViewById(R.id.currentState);

        // 위치 권한을 체크하고 요청하는 코드
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, YOUR_REQUEST_CODE);
        }

        // GPS 상태 확인 및 사용자에게 활성화 요청
        checkAndRequestGps();

        // TMapView 설정
        setupTMapView();


        bread = new Bread(this);
        bread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<TMapMarkerItem> breadMarkerItems = bread.getMarkerItems();
                tMapView.removeAllMarkerItem();

                tMapView.setZoomLevel(15);

                for(int i = 0; i<breadMarkerItems.size(); i++){
                    tMapView.addMarkerItem("bmarkerItem"+i, breadMarkerItems.get(i));
                }
                tMapView.setTrackingMode(true);
            }
        });

        grocery_button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              tMapView.removeAllMarkerItem();

              tMapView.setTrackingMode(false);
              tMapView.setZoomLevel(14);
          }
        });

        side_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        cafe = new Cafe(this);
        cafe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<TMapMarkerItem> cafeMarkerItems = cafe.getMarkerItems();
                tMapView.removeAllMarkerItem();

                tMapView.setZoomLevel(15);

                for(int i = 0; i<cafeMarkerItems.size(); i++)
                    tMapView.addMarkerItem("cmarkerItem"+i, cafeMarkerItems.get(i));
                tMapView.setTrackingMode(true);
            }
        });

        morning_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        korean_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        japanese_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        chinese_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        western_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        evening_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);
            }
        });

        // "확대" 버튼 클릭
        buttonZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.MapZoomIn();
            }
        });
        // "축소" 버튼 클릭
        buttonZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.MapZoomOut();
            }
        });
        buttoncurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();
                setCurrentPosition();
                tMapView.setZoomLevel(15);
                tMapView.setIconVisibility(true);
            }
        });

    }


    private void checkAndRequestGps() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // GPS가 활성화되어 있는 경우
            // 위치 업데이트 관련 코드 작성
        } else {
            // GPS가 비활성화되어 있는 경우, 사용자에게 GPS를 활성화하도록 요청
            showEnableGpsDialog();
        }
    }

    private void showEnableGpsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS가 비활성화되어 있습니다. GPS를 활성화하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("활성화", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // GPS 설정 화면으로 이동
                        Intent gpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(gpsIntent, YOUR_GPS_ENABLE_REQUEST_CODE);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setupTMapView() {
        linearLayoutTmap = findViewById(R.id.Tmap);
        tMapView = new TMapView(this);
        tMapView.setHttpsMode(true);

        tMapView.setSKTMapApiKey("kK62alKkLU8hRRtWeX7Uj5jpjWGbYZPVZAeSCCOi");
        linearLayoutTmap.addView(tMapView);

        tMapView.setIconVisibility(true);

        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapgps = new TMapGpsManager(MapActivity.this);
        tmapgps.setMinTime(1000);
        tmapgps.setMinDistance(5);
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);
        tmapgps.OpenGps();

        tMapView.setTrackingMode(true);

        setCurrentPosition();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 위치 업데이트 리스너 해제
        if (tmapgps != null) {
            tmapgps.CloseGps();
        }
    }

    @Override
    public void onLocationChange(Location location) {
        // 위치가 변경될 때 실행할 코드 작성
        // 여기에 원하는 작업을 추가하세요.
        Log.d(TAG, "Location changed: " + location.getLatitude() + ", " + location.getLongitude());
        // 예: 지도 이동, 마커 추가 등
    }

    private void setCurrentPosition() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        } else {
            // 위치를 가져올 수 없는 경우에 대한 처리
            Log.e(TAG, "Unable to get current location");
            // 여기에 위치를 가져올 수 없는 경우에 대한 메시지 또는 작업을 추가하세요.
        }
    }

    // onActivityResult 메서드에서 GPS 활성화 후의 작업을 처리할 수 있습니다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YOUR_GPS_ENABLE_REQUEST_CODE) {
            // GPS가 사용자에 의해 활성화되었는지 확인
            checkAndRequestGps();
        }
    }
}
