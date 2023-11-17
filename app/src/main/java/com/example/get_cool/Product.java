package com.example.get_cool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Product extends AppCompatActivity {

    private TextView text_price; //상품 가격
    private TextView text_num; //상품 수량

    private TextView or_price; //원가

    static int count;
    static int price = 0;
    static int res = 0;
    boolean i =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageView img = findViewById(R.id.imageView);
        TextView name = findViewById(R.id.textView);
        text_price = findViewById(R.id.textView3);
        or_price = findViewById(R.id.textView2);


        //파일 읽어오기
        FileInputStream inFs = null;
        String s ="";
        try {
            inFs = openFileInput("bread.txt");
            byte[] txt = new byte[30]; //byte[]형의 변수 txt를 선언
            inFs.read(txt); //읽어온 데이터를 저장
            s = new String(txt); //txt를 문자열로 변환
            inFs.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        s = s.trim();
        switch (s){
            case "ex1":
                res = 2970;
                price = 2970;
                or_price.setText("3500");
                img.setImageResource(R.drawable.ex1);
                name.setText("오지치즈후라이");
                text_price.setText(price+"");
                count = 1;
                break;
            case "ex2":
                res = 2200;
                price = 2200;
                or_price.setText("3100");
                img.setImageResource(R.drawable.ex2);
                name.setText("발로나초코 휘낭시에");
                text_price.setText(price+"");
                count = 1;
                break;
            case "ex3":
                res = 2200;
                price = 2200;
                or_price.setText("3100");
                img.setImageResource(R.drawable.ex3);
                name.setText("플레인 휘낭시에");
                text_price.setText(price+"");
                count = 1;
                break;
            case "ex4":
                res = 2200;
                price = 2200;
                or_price.setText("3200");
                img.setImageResource(R.drawable.ex4);
                name.setText("소금빵");
                text_price.setText(price+"");
                count = 1;
                break;
            case "ex5":
                res = 2970;
                price = 2970;
                or_price.setText("3300");
                img.setImageResource(R.drawable.ex5);
                name.setText("아메리칸 초코칩 쿠키");
                text_price.setText(price+"");
                count = 1;
                break;
            default:
                break;
        }

        //상품 가격,수량

        text_num = findViewById(R.id.text_count);
        Button btn_minus = (Button)findViewById(R.id.btn_minus); //-버튼
        Button btn_plus = (Button)findViewById(R.id.btn_plus); //+버튼
        text_num.setText("수량 : "+count+"");

        count = 1;

        //결제하기 버튼
        Button pay = (Button)findViewById(R.id.btn_pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // TextView에서 메뉴 이름을 가져오기
                    TextView nameTextView = findViewById(R.id.textView);
                    String selectedMenuName = nameTextView.getText().toString();

                    String s = String.valueOf(res);
                    FileOutputStream outputStream = openFileOutput("ex.txt",MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("selectedMenuName", selectedMenuName);
                    intent.putExtra("selectedCount", count);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //용기 선택 버튼

        ImageButton bowl1 = (ImageButton)findViewById(R.id.btn_bowl1);
        ImageButton bowl2 = (ImageButton)findViewById(R.id.btn_bowl2);
        bowl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == true){
                    bowl1.setImageResource(R.drawable.bowl2_check);
                    bowl2.setImageResource(R.drawable.bowl1);
                    res = (int) Math.round(price*count*0.94);
                    text_price.setText(res+"");
                    i = false;
                }else {
                    bowl1.setImageResource(R.drawable.bowl2);
                    res = price*count;
                    text_price.setText(res+"");
                    i = true;
                }
            }
        });
        bowl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == true){
                    bowl2.setImageResource(R.drawable.bowl1_check);
                    bowl1.setImageResource(R.drawable.bowl2);
                    res = price*count;
                    text_price.setText(res+"");
                    i = false;
                }else {
                    bowl2.setImageResource(R.drawable.bowl1);
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
                res = price*count;
                text_price.setText(res+"");
                text_num.setText("수량 : "+count+"");
            }
        });
        //+버튼
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count+1;
                res = price*count;
                text_price.setText(res+"");
                text_num.setText("수량 : "+count+"");
            }
        });
    }
}