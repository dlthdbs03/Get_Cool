package com.example.get_cool;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class PointPageActivity extends AppCompatActivity {

    private EditText numberInputEditText;
    private Button submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_page);

        // 배경색을 설정할 레이아웃 요소를 가져옴
        ConstraintLayout layout = findViewById(R.id.root); // 루트 레이아웃의 ID로 바꿔주세요

        // 배경색을 설정
        layout.setBackgroundColor(Color.parseColor("#D8D8D8"));

    }
}
