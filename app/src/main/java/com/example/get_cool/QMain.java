package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QMain extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private ImageView ivMenu; //좌측메뉴
    private ImageView ivAsk; //문의 작성
    private DrawerLayout drawerLayout; //메뉴
    private Toolbar toolbar; //상단바

    //하단바
    private TextView bottom_q;
    private ImageView bottom_map;
    private TextView bottom_my;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmain);
        //문의,계정,환불,기타 버튼
        Button button1 = (Button)findViewById(R.id.btn1);
        Button button2 = (Button)findViewById(R.id.btn2);
        Button button3 = (Button)findViewById(R.id.btn3);
        Button button4 = (Button)findViewById(R.id.btn4);
        //문의 작성 버튼
        ivAsk = findViewById(R.id.iv_ask);
        //메뉴
        ivMenu=findViewById(R.id.iv_menu);
        drawerLayout=findViewById(R.id.drawer);

        //상단바
        toolbar=findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        //하단바
        bottom_q=findViewById(R.id.bt_q);
        bottom_map=findViewById(R.id.bt_map);
        bottom_my=findViewById(R.id.bt_my);


        //버튼
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Inquiry.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Refund.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Account.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Etc.class);
                startActivity(intent);
            }
        });

        //문의 작성 페이지
        // 문의 작성 버튼
        if (ivAsk != null) {
            ivAsk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AskCreatePage.class);
                    startActivity(intent);
                }
            });
        } else {
            Log.e(TAG, "ivAsk is null. Check if the ID 'iv_ask' exists in your layout.");
        }


        //메뉴
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        //하단바
        bottom_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Product.class);
                startActivity(intent);
            }
        });
        bottom_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Inquiry.class);
                startActivity(intent);
            }
        });
        bottom_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Inquiry.class);
                startActivity(intent);
            }
        });

    }
}