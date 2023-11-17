package com.example.get_cool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class BusinessVerificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_verification);

        // "사업자 인증하기" 버튼
        Button buttonVerifyBusiness = findViewById(R.id.buttonVerifyBusiness);

        buttonVerifyBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입이 완료되었음을 알리는 메시지를 표시
                // 실제로는 여기에서 사업자 인증 로직을 수행하고, 성공한 경우 아래 메시지를 표시해야 합니다.
                showMessageAndReturnToLogin();
                saveUserDataToFile(getApplicationContext(), SignupActivity.getUsername(), SignupActivity.getPassword());
            }
        });

    }

    // 아이디와 비밀번호를 파일에 저장하는 메서드
    public static void saveUserDataToFile(Context context, String username, String password) {
        String userData = "Username: " + username + "\nPassword: " + password + "\n";

        try {
            FileOutputStream fos = context.openFileOutput("user_data.txt", Context.MODE_APPEND); // MODE_APPEND 모드로 열기
            fos.write(userData.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 회원가입 완료 메시지를 표시하고 로그인 화면으로 돌아가는 메서드
    private void showMessageAndReturnToLogin() {
        // 회원가입이 완료되었음을 알리는 메시지를 표시
        // 여기에서는 간단하게 Toast 메시지를 사용하겠습니다.
        Toast.makeText(this, "회원가입되었습니다.", Toast.LENGTH_SHORT).show();

        // 로그인 화면(LoginActivity)으로 돌아가기
        Intent intent = new Intent(BusinessVerificationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // 현재 액티비티 종료
    }
}
