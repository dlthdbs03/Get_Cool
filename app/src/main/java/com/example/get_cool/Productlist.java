package com.example.get_cool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class Productlist extends AppCompatActivity {
    ImageView back;
    ImageView heart;
    TextView aussie_name;



    boolean isHeartFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        back = findViewById(R.id.backButton);
        heart = findViewById(R.id.heart);
        aussie_name = findViewById(R.id.aussiefry_name);
        ImageView img1 = findViewById(R.id.aussiefry);
        ImageView img2 = findViewById(R.id.choco);
        ImageView img3 = findViewById(R.id.plain);
        ImageView img4 = findViewById(R.id.saltbread);
        ImageView img5 = findViewById(R.id.american);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Productlist.this, MainActivity.class);
                startActivity(intent);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isHeartFull) {
                    heart.setImageResource(R.drawable.heart_img);
                    isHeartFull = false;
                } else {
                    heart.setImageResource(R.drawable.full_heart);
                    isHeartFull = true;
                }
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = "ex1";
                    FileOutputStream outputStream = openFileOutput("bread.txt",MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    Intent intent = new Intent(getApplicationContext(), Product.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = "ex2";
                    FileOutputStream outputStream = openFileOutput("bread.txt",MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    Intent intent = new Intent(getApplicationContext(), Product.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = "ex3";
                    FileOutputStream outputStream = openFileOutput("bread.txt",MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    Intent intent = new Intent(getApplicationContext(), Product.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = "ex4";
                    FileOutputStream outputStream = openFileOutput("bread.txt",MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    Intent intent = new Intent(getApplicationContext(), Product.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = "ex5";
                    FileOutputStream outputStream = openFileOutput("bread.txt",MODE_PRIVATE);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                    Intent intent = new Intent(getApplicationContext(), Product.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });





    }

}