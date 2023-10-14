package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentStatePagerAdapter;


// 메인페이지 java 파일


public class MainActivity extends AppCompatActivity {

    private FragmentStatePagerAdapter vpAdapter;


    Fragment fragment_bakery, fragment_pastry, fragment_cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_bakery = new Fragment_bakery();
        fragment_pastry = new Fragment_pastry();
        fragment_cafe = new Fragment_cafe();

        //하단바
        TextView bottom_q = (TextView)findViewById(R.id.bt_q);
        ImageView bottom_map = (ImageView)findViewById(R.id.bt_map);
        TextView bottom_my = (TextView) findViewById(R.id.bt_my);

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
                Intent intent = new Intent(getApplicationContext(), QMain.class);
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

    }
}