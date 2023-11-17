package com.example.get_cool;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyIdentityActivity extends AppCompatActivity {

    private EditText editTextVerificationCode;
    private Button buttonVerifyCode;
    private TextView textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_identity);

        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);
        buttonVerifyCode = findViewById(R.id.buttonVerifyCode);
        textViewTimer = findViewById(R.id.textViewTimer);

        final Button buttonVerifyIdentity = findViewById(R.id.buttonVerifyIdentity);
        final CheckBox checkBoxUplus = findViewById(R.id.checkBoxUplus);
        final CheckBox checkBoxKT = findViewById(R.id.checkBoxKT);
        final CheckBox checkBoxSKT = findViewById(R.id.checkBoxSKT);

        buttonVerifyIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxUplus.isChecked() || checkBoxKT.isChecked() || checkBoxSKT.isChecked()) {
                    editTextVerificationCode.setVisibility(View.VISIBLE);
                    buttonVerifyCode.setVisibility(View.VISIBLE);
                    textViewTimer.setVisibility(View.VISIBLE);

                    new CountDownTimer(180000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            long minutes = millisUntilFinished / 60000;
                            long seconds = (millisUntilFinished % 60000) / 1000;
                            String timeLeft = String.format("%d:%02d", minutes, seconds);
                            textViewTimer.setText(timeLeft);
                        }

                        public void onFinish() {
                            textViewTimer.setText("다시 인증해 주세요");
                            // 타이머 종료 후 추가 작업을 여기에 추가하세요.
                        }
                    }.start();
                } else {
                    // 통신사 선택이 되어 있지 않을 때 처리 (예: 경고 메시지 표시)
                    Toast.makeText(getApplicationContext(), "통신사를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredVerificationCode = editTextVerificationCode.getText().toString();
                if (enteredVerificationCode.equals("000000")) {
                    // 인증 성공 처리
                    Toast.makeText(getApplicationContext(), "인증 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyIdentityActivity.this, BusinessVerificationActivity.class);
                    startActivity(intent);

                    // 여기에 인증 완료와 관련된 추가 로직을 구현하세요.
                } else {
                    // 인증 실패 처리 (예: 오류 메시지 표시)
                    Toast.makeText(getApplicationContext(), "인증번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
