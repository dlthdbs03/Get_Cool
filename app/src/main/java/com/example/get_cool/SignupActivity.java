package com.example.get_cool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private TextView textViewIdMatch;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private TextView textViewPasswordMatch;
    private Button buttonVerifyIdentity;
    private Button buttonCheckDuplicate;

    private static String savedUsername = ""; // 저장한 아이디
    private static String savedPassword = ""; // 저장한 비밀번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        textViewIdMatch = findViewById(R.id.textViewIdMessage);
        textViewPasswordMatch = findViewById(R.id.textViewPasswordMatch);
        buttonVerifyIdentity = findViewById(R.id.buttonVerifyIdentity);
        buttonCheckDuplicate = findViewById(R.id.buttonCheckDuplicate);



        // "본인인증" 버튼 클릭 시 처리
        buttonVerifyIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 본인인증 페이지로 이동하는 코드를 여기에 추가
                // Intent를 사용하여 본인인증 화면으로 이동합니다.
                savedUsername = editTextUsername.getText().toString();
                savedPassword = editTextPassword.getText().toString();
                Intent intent = new Intent(SignupActivity.this, VerifyIdentityActivity.class);
                startActivity(intent);
            }
        });


        // 아이디 입력란에서 엔터 키를 누를 때 이벤트 처리
        editTextUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    // 엔터 키가 눌렸을 때 처리할 작업을 여기에 추가
                    return true; // true를 반환하여 이벤트를 소비하고 무시
                }
                return false; // 다른 키 입력 이벤트는 기본 동작을 수행
            }
        });

        // 비밀번호 입력란에서 입력이 변경될 때마다 호출되는 TextWatcher 추가
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력 전
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력 중
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력 후
                checkPassword();
                checkPasswordMatch();
            }
        });

        editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력 전
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력 중
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 입력 후
                checkPasswordMatch();
            }
        });

        // "중복확인" 버튼 클릭 시 처리
        buttonCheckDuplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이디 중복 확인 로직을 수행
                String username = editTextUsername.getText().toString();

                // 예시: 아이디 형식을 정규식으로 확인 (예: 첫 글자는 영문, 5~12자 길이)
                Pattern pattern = Pattern.compile("^[a-z][a-z0-9]{4,11}$");

                if (pattern.matcher(username).matches()) {
                    // 아이디 형식이 정규식과 일치하는 경우

                    // user_data.txt 파일에서 저장된 아이디를 검사
                    String[] userData = getUserDataFromFile(getApplicationContext());
                    if (userData != null) {
                        for (String line : userData) {
                            if (line.startsWith("Username:")) {
                                String existingUsername = line.substring("Username: ".length()).trim();
                                if (existingUsername.equals(username)) {
                                    textViewIdMatch.setText("아이디가 중복됩니다.");
                                    textViewIdMatch.setTextColor(getResources().getColor(R.color.red));
                                    return; // 중복 아이디이므로 처리 종료
                                }
                            }
                        }
                    }

                    // 중복이 아니라고 가정하고 메시지 표시

                    textViewIdMatch.setText("사용가능한 아이디 입니다.");
                    textViewIdMatch.setTextColor(getResources().getColor(R.color.green));
                    buttonCheckDuplicate.setEnabled(false); // 중복이 아니면 버튼을 비활성화
                } else {
                    // 아이디 형식이 정규식과 일치하지 않는 경우
                    textViewIdMatch.setText("올바른 아이디 형식이 아닙니다.");
                }
            }
        });
    }

    // 비밀번호 규격을 확인하고 메시지를 표시하는 메서드
    private void checkPassword() {
        String password = editTextPassword.getText().toString();

        // 예시: 비밀번호는 8자 이상, 대문자, 소문자, 숫자, 특수 문자를 포함해야 함
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

        if (pattern.matcher(password).matches()) {
            // 비밀번호 규격에 맞음
            textViewPasswordMatch.setText("비밀번호 규격이 맞습니다.");
            textViewPasswordMatch.setTextColor(getResources().getColor(R.color.green)); // 텍스트 컬러를 초록색으로 설정
        } else {
            // 비밀번호 규격에 맞지 않음
            textViewPasswordMatch.setText("비밀번호 규격을 확인하세요.");
            textViewPasswordMatch.setTextColor(getResources().getColor(R.color.red)); // 텍스트 컬러를 빨간색으로 설정
        }
    }

    // 비밀번호와 확인 비밀번호를 비교하고 메시지를 표시하는 메서드
    private void checkPasswordMatch() {
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (password.equals(confirmPassword)) {
            // 비밀번호 일치
            textViewPasswordMatch.setText("비밀번호가 일치합니다.");
            textViewPasswordMatch.setTextColor(getResources().getColor(R.color.green)); // 텍스트 컬러를 초록색으로 설정
            buttonVerifyIdentity.setEnabled(true);
        } else {
            // 비밀번호 불일치
            textViewPasswordMatch.setText("비밀번호가 일치하지 않습니다.");
            textViewPasswordMatch.setTextColor(getResources().getColor(R.color.red)); // 텍스트 컬러를 빨간색으로 설정
        }
    }



    public static String[] getUserDataFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput("user_data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            StringBuilder userData = new StringBuilder();

            while ((line = br.readLine()) != null) {
                userData.append(line).append("\n");
            }

            br.close();
            isr.close();
            fis.close();

            return userData.toString().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 사용자가 입력한 아이디와 비밀번호를 저장
        outState.putString("username", editTextUsername.getText().toString());
        outState.putString("password", editTextPassword.getText().toString());
        outState.putString("confirmPassword", editTextConfirmPassword.getText().toString());
    }

    public void setUsername(String savedUsername ) {
        this.savedUsername = savedUsername;
    }

    public static String getUsername() {
        return savedUsername;
    }

    public void setPassword(String savedPassword ) {
        this.savedPassword = savedPassword ;
    }

    public static String getPassword() {
        return savedPassword;
    }
}
