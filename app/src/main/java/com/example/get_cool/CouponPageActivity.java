package com.example.get_cool;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CouponPageActivity extends AppCompatActivity {

    private EditText numberInputEditText;
    private Button submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_page);

        numberInputEditText = findViewById(R.id.input_text);
        submitButton = findViewById(R.id.input_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered number from the EditText
                String numberStr = numberInputEditText.getText().toString();

                if (!numberStr.isEmpty()) {
                    int enteredNumber = Integer.parseInt(numberStr);

                    // Do something with the entered number
                    // For example, display it, perform calculations, etc.
                }
            }
        });
    }
}
