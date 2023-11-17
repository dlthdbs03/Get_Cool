package com.example.get_cool;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrderPageActivity extends AppCompatActivity {

    private RecyclerView orderRecyclerView;
    private OrderListAdapter orderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        // RecyclerView 초기화
        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 주문 내역 불러와서 RecyclerView에 설정
        loadOrderHistory();
    }

    // 주문 내역을 불러와서 RecyclerView에 설정
    private void loadOrderHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("order_history", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("order_list", null);

        List<OrderItem> orderItemList = new ArrayList<>();

        if (json != null) {
            Type type = new TypeToken<List<OrderItem>>() {}.getType();
            orderItemList = gson.fromJson(json, type);
        }

        // RecyclerView 어댑터에 데이터 설정
        orderListAdapter = new OrderListAdapter(this, orderItemList);
        orderRecyclerView.setAdapter(orderListAdapter);
    }
}
