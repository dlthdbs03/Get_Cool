package com.example.get_cool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentStatePagerAdapter;


// 메인페이지 java 파일


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentStatePagerAdapter vpAdapter;
    private ImageView ivMenu; //좌측메뉴
    private ImageView ivSearch; //검색
    private DrawerLayout drawerLayout; //메뉴
    private NavigationView navigationView;
    private Toolbar toolbar; //상단바

    Fragment fragment_bakery, fragment_pastry, fragment_cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_bakery = new Fragment_bakery();
        fragment_pastry = new Fragment_pastry();
        fragment_cafe = new Fragment_cafe();

        ivMenu = findViewById(R.id.iv_menu);
        drawerLayout = findViewById(R.id.drawer_main);
        navigationView = findViewById(R.id.navigation);
        ivSearch = findViewById(R.id.iv_search);
        toolbar = findViewById(R.id.top_menu);

        setSupportActionBar(toolbar);

        //하단바
        TextView bottom_q = (TextView) findViewById(R.id.bt_q);
        ImageView bottom_map = (ImageView) findViewById(R.id.bt_map);
        TextView bottom_my = (TextView) findViewById(R.id.bt_my);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navigationView = (NavigationView) findViewById(R.id.navigation);

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

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment_bakery).commit();

        TabLayout tabs = findViewById(R.id.tabs);



        // 베이커리, 제과, 카페 각 버튼을 누르면 해당 Fragment로 이동

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;

                if (position == 0) {
                    selected = fragment_bakery;
                } else if (position == 1) {
                    selected = fragment_pastry;
                } else if (position == 2) {
                    selected = fragment_cafe;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, selected);
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        TextView bt_my =  findViewById(R.id.bt_my);

        bt_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
            }
        });


    }

    public void onClickbakery(View v) {
        Intent intent = new Intent(getApplicationContext(), Productlist.class);
        startActivity(intent);
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

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
//        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}