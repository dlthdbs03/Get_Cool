package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading extends AppCompatActivity {

    private static final int LOADING_TIME = 1000; // 로딩 화면을 표시할 시간 (밀리초)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // 일정 시간 후에 MainActivity로 이동
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Intent = new Intent(Loading.this, MainActivity.class);
                startActivity(Intent);
                finish(); // 현재 액티비티 종료
            }
        }, LOADING_TIME);
    }
}