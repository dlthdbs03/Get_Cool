package com.example.get_cool;

import static com.example.get_cool.Product.count;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    private EditText pointsEditText;
    private EditText requestEditText;
    private Button useAllPointsButton;
    private TextView availablePointsTextView;
    private TextView paymentEditText;
    private Button payButton;
    private Spinner couponSpinner;

    ImageButton back;

    private int totalPoints = 1000; // 예시로 1000 포인트를 가정
    private int totalPayment = 10000; // 예시로 10000 결제금액 가정


    private List<Coupon> availableCoupons = new ArrayList<>(); // 사용 가능한 쿠폰 목록

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //가격 읽어오기
        FileInputStream inFs = null;
        String s ="";
        try {
            inFs = openFileInput("ex.txt");
            byte[] txt = new byte[30]; //byte[]형의 변수 txt를 선언
            inFs.read(txt); //읽어온 데이터를 저장
            s = new String(txt); //txt를 문자열로 변환

            inFs.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String price = s.trim();
        int totalPrice = Integer.parseInt(price);

        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, Product.class);
                startActivity(intent);
            }
        });


        // 쿠폰 클래스 및 쿠폰 종류 추가
        availableCoupons.add(new Coupon("상관 없음", 0, CouponType.NONE));
        availableCoupons.add(new Coupon("10% 할인 쿠폰", 10, CouponType.PERCENT_DISCOUNT));
        availableCoupons.add(new Coupon("20% 할인 쿠폰", 20, CouponType.PERCENT_DISCOUNT));
        availableCoupons.add(new Coupon("5,000원 할인 쿠폰", 5000, CouponType.AMOUNT_DISCOUNT));
        availableCoupons.add(new Coupon("무료 배송 쿠폰", 0, CouponType.SHIPPING_DISCOUNT));

        // 레이아웃 요소와 연결
        pointsEditText = findViewById(R.id.pointsEditText);
        paymentEditText = findViewById(R.id.totalPayment);
        useAllPointsButton = findViewById(R.id.useAllPointsButton);
        availablePointsTextView = findViewById(R.id.availablePointsTextView);
        requestEditText = findViewById(R.id.requestEditText);
        payButton = findViewById(R.id.payButton);
        couponSpinner = findViewById(R.id.couponSpinner);

        // 기본값 설정
        pointsEditText.setHint("사용하실 포인트를 입력하세요");
        pointsEditText.setText(""); // 빈 문자열로 초기화


        // 기존 글씨 초기화
        availablePointsTextView.setText("보유 포인트: " + totalPoints);
        paymentEditText.setText("총액 : " + price + "원");
        payButton.setText(totalPrice + "원 결제하기");

        // 결제수단 드롭박스에 예시 추가
        Spinner paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        ArrayAdapter<String> paymentMethodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        paymentMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodAdapter.add("신용카드");
        paymentMethodAdapter.add("계좌이체");
        paymentMethodAdapter.add("휴대폰결제");
        paymentMethodAdapter.add("만나서결제");
        paymentMethodSpinner.setAdapter(paymentMethodAdapter);

        // 쿠폰 드롭박스에 예시 추가
        ArrayAdapter<String> couponAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        couponAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Coupon coupon : availableCoupons) {
            couponAdapter.add(coupon.getName());
        }

        couponSpinner.setAdapter(couponAdapter);

        // 포인트 사용 입력 리스너
        pointsEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String enteredPointsStr = pointsEditText.getText().toString();

                if (enteredPointsStr.isEmpty()) {
                    Toast.makeText(PaymentActivity.this, "포인트를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    int enteredPoints = Integer.parseInt(enteredPointsStr);

                    if (enteredPoints > totalPoints) {
                        Toast.makeText(PaymentActivity.this, "가지고 있는 포인트가 적습니다", Toast.LENGTH_SHORT).show();
                        pointsEditText.setText(String.valueOf(totalPoints));
                    }
                }

                pointsEditText.clearFocus();
                updatePaymentButtonText(String.valueOf(totalPrice)); // 결제 버튼 텍스트 업데이트
                return true;
            }
            return false;
        });

        // 요청사항 입력에서 엔터 키 처리
        requestEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideSoftKeyboard();
                return true;
            }
            return false;
        });

        // 전체 포인트 사용 버튼 클릭 리스너
        useAllPointsButton.setOnClickListener(v -> {
            pointsEditText.setText(String.valueOf(totalPoints));
            updatePaymentButtonText(String.valueOf(totalPrice)); // 결제 버튼 텍스트 업데이트
        });

        // 쿠폰 선택 리스너
        couponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatePaymentButtonText(String.valueOf(totalPrice)); // 결제 버튼 텍스트 업데이트
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // 아무것도 선택되지 않았을 때 처리
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 선택된 결제 금액 및 기타 정보 가져오기
                if (payButton != null) {
                    String totalPrice = payButton.getText().toString().replace("원 결제하기", ""); // "원 결제하기" 문자열 제거
                    Intent intent = new Intent(PaymentActivity.this, CompletePayment.class);

                    // Intent에 데이터 추가
                    intent.putExtra("selectedMenuName", getIntent().getStringExtra("selectedMenuName"));
                    intent.putExtra("selectedCount", getIntent().getIntExtra("selectedCount", count));
                    intent.putExtra("totalPrice", totalPrice);

                    startActivity(intent);
                }
            }
        });
    }

    // 결제 버튼 텍스트 업데이트
    private void updatePaymentButtonText(String price) {
        String enteredPointsStr = pointsEditText.getText().toString();
        totalPayment = Integer.parseInt(price);
        int usedPoints = 0;
        if (!enteredPointsStr.isEmpty()) {
            usedPoints = Integer.parseInt(enteredPointsStr);
        }

        int remainingPayment = totalPayment;
        String selectedCouponName = (String) couponSpinner.getSelectedItem();

        // 선택된 쿠폰 찾기
        Coupon selectedCoupon = null;
        for (Coupon coupon : availableCoupons) {
            if (coupon.getName().equals(selectedCouponName)) {
                selectedCoupon = coupon;
                break;
            }
        }

        // 쿠폰 적용
        if (selectedCoupon != null) {
            int discountValue = selectedCoupon.getDiscountValue();
            CouponType couponType = selectedCoupon.getType();

            if (couponType == CouponType.PERCENT_DISCOUNT) {
                // 퍼센트 할인 쿠폰
                remainingPayment -= (totalPayment * discountValue / 100);
            } else if (couponType == CouponType.AMOUNT_DISCOUNT) {
                // 금액 할인 쿠폰
                remainingPayment -= discountValue;
            } else if (couponType == CouponType.SHIPPING_DISCOUNT) {
                // 배송비 할인 쿠폰
                // 할인 코드 추가
            }
        }

        // 결제 금액에서 포인트 차감
        if (usedPoints > 0) {
            remainingPayment -= usedPoints;
        }

        // 결제 버튼 텍스트 업데이트--
        payButton.setText(remainingPayment + "원 결제하기");
    }

    // 소프트 키보드 숨기기
    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(pointsEditText.getWindowToken(), 0);
        }
    }
}

