package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Product extends AppCompatActivity {

    private TextView text_price; //상품 가격
    private TextView text_num; //상품 수량
    static int count;
    boolean i =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product2);

        //상품 가격,수량
        text_price = findViewById(R.id.textView3);
        text_num = findViewById(R.id.text_count);
        Button btn_minus = (Button)findViewById(R.id.btn_minus); //-버튼
        Button btn_plus = (Button)findViewById(R.id.btn_plus); //+버튼
        int price = 2970;
        count = 1;
        text_price.setText(price+"");
        text_num.setText("수량 : "+count+"");

        //결제하기 버튼
        Button pay = (Button)findViewById(R.id.btn_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //용기 선택 버튼

        ImageButton bowl1 = (ImageButton)findViewById(R.id.btn_bowl1);
        ImageButton bowl2 = (ImageButton)findViewById(R.id.btn_bowl2);
        bowl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == true){
                    bowl1.setImageResource(R.drawable.ex);
                    i = false;
                }else {
                    bowl1.setImageResource(R.drawable.bowl2);
                    i = true;
                }
            }
        });
        bowl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == true){
                    bowl2.setImageResource(R.drawable.ex);
                    i = false;
                }else {
                    bowl2.setImageResource(R.drawable.bowl);
                    i = true;
                }
            }
        });


        //-버튼
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count-1;
                if (count<0){
                    count=0;
                }
                text_price.setText(price*count +"");
                text_num.setText("수량 : "+count+"");
            }
        });
        //+버튼
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count+1;
                text_price.setText(price*count+"");
                text_num.setText("수량 : "+count+"");
            }
        });
    }
}
