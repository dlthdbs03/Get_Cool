package com.example.get_cool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.get_cool.MainBusinessActivity;
import com.example.get_cool.SignupActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textViewMessage;
    private static String savedUsername = null;
    private static String savedPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewMessage = findViewById(R.id.textViewMessage);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // 여기에서 사용자 확인 로직을 수행해야 합니다.
                // 사용자 데이터를 실제 데이터베이스나 파일에서 읽어올 필요가 있습니다.

                // 파일에서 사용자 데이터를 읽어오는 함수 호출
                if (checkUserData(LoginActivity.this,username, password)) {
                    // 로그인 성공
                    // 여기에서 메인 비지니스 엑티비티로 이동
                    Intent intent = new Intent(LoginActivity.this, MainBusinessActivity.class);
                    startActivity(intent);
                } else {
                    // 로그인 실패
                    textViewMessage.setText("존재하지 않는 사용자입니다.");
                }
            }
        });

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원 가입 버튼 클릭 시 회원 가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public static boolean checkUserData(Context context, String username, String password) {
        try {
            FileInputStream fis = context.openFileInput("user_data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            boolean userExists = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    savedUsername = line.substring(10); // "Username: " 부분을 제외한 아이디
                } else if (line.startsWith("Password: ")) {
                    savedPassword = line.substring(10); // "Password: " 부분을 제외한 비밀번호
                }

                // 아이디와 비밀번호가 모두 읽혔을 때, 비교
                if (savedUsername != null && savedPassword != null) {
                    if (username.equals(savedUsername) && password.equals(savedPassword)) {
                        userExists = true;
                        break;
                    }
                    // 비교 후 초기화
                    savedUsername = null;
                    savedPassword = null;
                }
            }

            br.close();
            isr.close();
            fis.close();

            return userExists;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getUsername() {
        return savedUsername;
    }


}
