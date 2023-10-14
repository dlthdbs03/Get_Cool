package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.Manifest;
import android.widget.TextView;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    private static final String TAG = "Main_Activity";
    private static final int YOUR_REQUEST_CODE = 1;
    private TMapView tMapView;
    private LinearLayout linearLayoutTmap;
    private TMapGpsManager tmapgps = null;
    private View view;
    private boolean TrackingMode = true;

    private DrawerLayout drawerLayout; //메뉴
    private Toolbar toolbar; //상단바
    private ImageView ivMenu; //좌측메뉴
    private ImageView ivSearch; //검색
    //하단바
    private TextView bottom_q;
    private ImageView bottom_map;
    private TextView bottom_my;

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

        // 검색
        ivSearch = findViewById(R.id.iv_search);
        //메뉴
        ivMenu=findViewById(R.id.iv_menu);

        toolbar=findViewById(R.id.toolbar);

        //LinearLayout linearLayoutmenu = findViewById(R.id.menu);
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


        linearLayoutTmap = findViewById(R.id.Tmap); // findViewById를 통해 linearLayoutTmap을 초기화합니다.
        tMapView = new TMapView(this);
        tMapView.setHttpsMode(true);

        tMapView.setSKTMapApiKey("kK62alKkLU8hRRtWeX7Uj5jpjWGbYZPVZAeSCCOi");
        linearLayoutTmap.addView(tMapView);


        /* 현재 보는 방향으로 설정 */
        tMapView.setCompassMode(true);
        /* 현 위치 아이콘 설정 */
        tMapView.setIconVisibility(true);

        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        // 위치 권한을 체크하고 요청하는 코드
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, YOUR_REQUEST_CODE);
        }

        tmapgps = new TMapGpsManager(MapActivity.this);
        tmapgps.setMinTime(1000);
        tmapgps.setMinDistance(5);
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);
        //tmapgps.OpenGps();

        tMapView.setSightVisible(true);

        //하단바
        bottom_q=findViewById(R.id.bt_q);
        bottom_map=findViewById(R.id.bt_map);
        bottom_my=findViewById(R.id.bt_my);

        //문의 작성 페이지
        /*ivSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AskCreatePage.class);
                startActivity(intent);
            }
        });

        //메뉴
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });*/

        bread = new Bread(this);
        bread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<TMapMarkerItem> breadMarkerItems = bread.getMarkerItems();
                tMapView.removeAllMarkerItem();

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);

                for(int i = 0; i<breadMarkerItems.size(); i++){
                    tMapView.addMarkerItem("bmarkerItem"+i, breadMarkerItems.get(i));
                }

                tMapView.setCenterPoint(127.035805, 37.300655);
                tMapView.setIconVisibility(false);
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

                tMapView.setTrackingMode(false);
                tMapView.setZoomLevel(14);

                for(int i = 0; i<cafeMarkerItems.size(); i++)
                    tMapView.addMarkerItem("cmarkerItem"+i, cafeMarkerItems.get(i));

                tMapView.setCenterPoint(127.035805, 37.300655);
                tMapView.setIconVisibility(false);
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
                setCurrentPosition();
                tMapView.setZoomLevel(15);
                tMapView.setTrackingMode(true);
                tMapView.setIconVisibility(true);
            }
        });

        //하단바
        /*bottom_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QMain.class);
                startActivity(intent);
            }
        });
        bottom_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
        /*bottom_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Inquiry.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onLocationChange(Location location) {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
    }

    private void setCurrentPosition() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
    }
}
