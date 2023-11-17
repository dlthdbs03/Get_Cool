package com.example.get_cool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainBusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_business);

        Button buttonInventoryManagement = findViewById(R.id.buttonInventoryManagement);
        buttonInventoryManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "재고 추가" 액티비티로 이동
                Intent intent = new Intent(MainBusinessActivity.this, InventoryAddActivity.class);
                startActivity(intent);
            }
        });

        // "Logout" 버튼 클릭 시 처리
        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그아웃 처리 및 로그인 화면으로 이동
                Intent intent = new Intent(MainBusinessActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });
    }
}
