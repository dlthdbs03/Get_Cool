package com.example.get_cool;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;

import com.bumptech.glide.Glide;


public class MyPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView ivMenu; //좌측메뉴
    private ImageView ivSearch; //검색
    private DrawerLayout drawerLayout; //메뉴
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ivMenu = findViewById(R.id.mypage_menu);
        drawerLayout = findViewById(R.id.drawer_my);
        ivSearch = findViewById(R.id.mypage_search);

        //하단바
        TextView bottom_q = (TextView)findViewById(R.id.bt_q);
        ImageView bottom_map = (ImageView)findViewById(R.id.bt_map);
        TextView bottom_my = (TextView) findViewById(R.id.bt_my);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navigationView = (NavigationView) findViewById(R.id.navigation_my);

        if(navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        bottom_q.setOnClickListener(new View.OnClickListener() {
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
        bottom_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });


        ImageView modify =  findViewById(R.id.modify);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, ModifyPageActivity.class);
                startActivity(intent);
            }
        });

        TextView user_order =  findViewById(R.id.user_order);
        TextView user_point =  findViewById(R.id.user_point);
        TextView user_coupon =  findViewById(R.id.user_coupon);

        user_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, OrderPageActivity.class);
                startActivity(intent);
            }
        });
        user_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p) {
                Intent intent = new Intent(MyPageActivity.this, PointPageActivity.class);
                startActivity(intent);
            }
        });
        user_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                Intent intent = new Intent(MyPageActivity.this, CouponPageActivity.class);
                startActivity(intent);
            }
        });



        TextView notice =  findViewById(R.id.notice);
        TextView ask =  findViewById(R.id.ask);

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                Intent intent = new Intent(MyPageActivity.this, NoticePageActivity.class);
                startActivity(intent);
            }
        });
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                Intent intent = new Intent(MyPageActivity.this, AskPageActivity.class);
                startActivity(intent);
            }
        });


        // EditText 위젯을 찾습니다.
        TextView editTextName1 = findViewById(R.id.editText_name);
        TextView editTextName2 = findViewById(R.id.editText_name2);
        TextView editTextName3 = findViewById(R.id.editText_name3);
        TextView editTextLevel = findViewById(R.id.editText_level);
        TextView editTextPoint = findViewById(R.id.editText_point);
        TextView editTextCoupon = findViewById(R.id.editText_coupon);

        // ImageView 위젯을 찾습니다.
        ImageView profileImg = findViewById(R.id.profile_img);

        // 'raw' 폴더에서 my_data.txt 파일을 가져와서 InputStream으로 열기
        InputStream inputStream = getResources().openRawResource(R.raw.my_data);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("이름:")) {
                    editTextName1.setText(line.substring(4).trim());
                    editTextName2.setText(line.substring(4).trim());
                    editTextName3.setText(line.substring(4).trim());
                } else if (line.startsWith("등급:")) {
                    editTextLevel.setText(line.substring(4).trim());
                } else if (line.startsWith("포인트:")) {
                    editTextPoint.setText(line.substring(5).trim());
                } else if (line.startsWith("쿠폰:")) {
                    editTextCoupon.setText(line.substring(4).trim());
                } else if (line.startsWith("이미지:")) {
                    String imageUrl = line.substring(5).trim();
                    // 이미지를 Glide를 사용하여 로드하여 ImageView에 설정합니다.
                    Glide.with(this).load(imageUrl).into(profileImg);
                }
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Log.d("MainActivity", "Item Clicked: " + id); // 디버깅을 위한 이 줄을 추가합니다.

        if (id==R.id.menu_home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_mypage) {
            Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_map) {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_inquiry) {
            Intent intent = new Intent(getApplicationContext(), QMain.class);
            startActivity(intent);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_my);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}