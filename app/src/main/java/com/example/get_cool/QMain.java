package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QMain extends AppCompatActivity {

    //fragment
    private FragmentManager fragmentManager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private FragmentTransaction transaction;


    private ImageView ivMenu; //좌측메뉴
    private ImageView ivAsk; //문의 작성
    private DrawerLayout drawerLayout; //메뉴
    private Toolbar toolbar; //상단바

    //하단바
    private TextView bottom_q;
    private ImageView bottom_map;
    private TextView bottom_my;
    //문의,계정,환불,기타 버튼



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmain);

        //fragment
        fragmentManager = getSupportFragmentManager();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment1).commitAllowingStateLoss();



        //문의 작성 버튼
        ivAsk = findViewById(R.id.iv_ask);
        //메뉴
        ivMenu=findViewById(R.id.iv_menu);
        drawerLayout=findViewById(R.id.drawer_qmain);

        //상단바
        toolbar=findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        //하단바
        bottom_q=findViewById(R.id.bt_q);
        bottom_map=findViewById(R.id.bt_map);
        bottom_my=findViewById(R.id.bt_my);


        //문의 작성 페이지
        ivAsk.setOnClickListener(new View.OnClickListener() {

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
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        //하단바
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

    }
    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();
        Button button1 = (Button)findViewById(R.id.btn1);
        Button button2 = (Button)findViewById(R.id.btn2);
        Button button3 = (Button)findViewById(R.id.btn3);
        Button button4 = (Button)findViewById(R.id.btn4);
        button1.setBackgroundResource(R.drawable.btn_style);
        button2.setBackgroundResource(R.drawable.btn_style);
        button3.setBackgroundResource(R.drawable.btn_style);
        button4.setBackgroundResource(R.drawable.btn_style);

        if (view.getId() == R.id.btn1) {
            transaction.replace(R.id.frameLayout, fragment1).commit();
            button1.setBackgroundResource(R.drawable.btn_pressed);
        }
        else if (view.getId() == R.id.btn2) {
            transaction.replace(R.id.frameLayout, fragment2).commit();
            button2.setBackgroundResource(R.drawable.btn_pressed);
        }
        else if (view.getId() == R.id.btn3) {
            transaction.replace(R.id.frameLayout, fragment3).commit();
            button3.setBackgroundResource(R.drawable.btn_pressed);
        }
        else if (view.getId() == R.id.btn4) {
            transaction.replace(R.id.frameLayout, fragment4).commit();
            button4.setBackgroundResource(R.drawable.btn_pressed);
        }
    }
}