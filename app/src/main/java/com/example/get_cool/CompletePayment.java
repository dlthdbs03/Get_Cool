package com.example.get_cool;

import static com.example.get_cool.Product.count;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CompletePayment extends AppCompatActivity {
    private ImageView close;
    private TextView menu_name;
    private TextView totalPay;
    private Button orderbutton;

    // Add this variable to access OrderListAdapter
    private OrderListAdapter orderListAdapter;
    private List<OrderItem> orderItemList;  // 주문 내역 리스트 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_payment);

        close = findViewById(R.id.closebutton);
        menu_name = findViewById(R.id.menu_name);
        totalPay = findViewById(R.id.totalPay);
        orderbutton = findViewById(R.id.pay_orderbutton);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletePayment.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Intent에서 데이터 추출
        Intent intent = getIntent();
        if (intent != null) {
            String selectedMenuName = intent.getStringExtra("selectedMenuName");
            int selectedCount = intent.getIntExtra("selectedCount", count);
            String totalPrice = intent.getStringExtra("totalPrice");

            if (selectedMenuName != null) {
                // 이제 selectedMenuName 변수에 전달된 메뉴 이름이 들어 있습니다.
                // 여기에서 필요한 작업 수행
                menu_name.setText(selectedMenuName + "*" + selectedCount); // menu_name에 값을 표시하는 예시
            }

            if (totalPrice != null) {
                // 이제 totalPrice 변수에 전달된 결제 금액이 들어 있습니다.
                // 여기에서 필요한 작업 수행
                totalPay.setText(totalPrice + "원"); // totalPay에 값을 표시하는 예시
            }

            // orderItemList 초기화
            orderItemList = new ArrayList<>();
            // 주문된 항목 추가
            orderItemList.add(new OrderItem(selectedMenuName, totalPrice));
        }

        onOrderComplete(orderItemList);


        orderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start OrderPageActivity
                Intent intent = new Intent(CompletePayment.this, OrderPageActivity.class);
                startActivity(intent);
            }
        });

    }

    // CompletePayment 액티비티에서
    // 주문이 완료되면 해당 주문 내역을 SharedPreferences에 저장
    private void saveOrderHistory(List<OrderItem> orderItemList) {
        SharedPreferences sharedPreferences = getSharedPreferences("order_history", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(orderItemList);

        editor.putString("order_list", json);
        editor.apply();
    }

    private void onOrderComplete(List<OrderItem> newOrderItemList) {
        // 기존 주문 내역을 불러옴
        List<OrderItem> existingOrderList = loadOrderHistory();

        // 만약 기존 주문 내역이 null이면 새로운 주문 내역으로 초기화
        if (existingOrderList == null) {
            existingOrderList = new ArrayList<>();
        }

        // 새로운 주문 내역을 기존 주문 내역에 추가
        existingOrderList.addAll(newOrderItemList);

        // 현재 날짜를 가져옴
        String currentDate = getCurrentDate();

        // 새로운 주문 내역을 저장
        saveOrderHistory(existingOrderList);
    }


    // 기존 주문 내역을 불러오는 메서드
    private List<OrderItem> loadOrderHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("order_history", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("order_list", "");
        Type type = new TypeToken<List<OrderItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // 현재 날짜를 yy.mm.dd 형식으로 가져오는 메서드
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd", Locale.getDefault());
        return sdf.format(new Date());
    }
}