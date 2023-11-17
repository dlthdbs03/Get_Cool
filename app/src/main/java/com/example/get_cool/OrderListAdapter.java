package com.example.get_cool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {

    private List<OrderItem> orderItemList;
    private HashMap<String, Integer> menuImageMap;
    private Context context;

    public OrderListAdapter(Context context, List<OrderItem> orderItemList) {
        this.context = context;
        this.orderItemList = orderItemList;

        // 메뉴 이름과 이미지를 매핑하는 HashMap 초기화
        initMenuImageMap();
    }

    private void initMenuImageMap() {
        menuImageMap = new HashMap<>();
        menuImageMap.put("오지치즈후라이 휘낭시에", R.drawable.ex1);
        menuImageMap.put("발로나초코 휘낭시에", R.drawable.ex2);
        menuImageMap.put("플레인 휘낭시에", R.drawable.ex3);
        menuImageMap.put("소금빵", R.drawable.ex4);
        menuImageMap.put("아메리칸 초코칩 쿠키", R.drawable.ex5);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);
        holder.bind(orderItem);

        // 이미지 설정
        int imageResId = getImageResourceId(orderItem.getMenuName());
        holder.menuImageView.setImageResource(imageResId);
    }

    private int getImageResourceId(String menuName) {
        // 기본 이미지 리소스 ID 설정
        int defaultImageResId = R.drawable.ex1;
        return menuImageMap.getOrDefault(menuName, defaultImageResId);
    }


    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView todayDate;
        private TextView menuNameTextView;
        private TextView priceTextView;
        private ImageView menuImageView; // 추가된 부분

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            todayDate = itemView.findViewById(R.id.date);
            menuNameTextView = itemView.findViewById(R.id.menuNameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            menuImageView = itemView.findViewById(R.id.menuImageView); // 추가된 부분
        }

        public void bind(OrderItem orderItem) {
            todayDate.setText(orderItem.getOrderDate());
            menuNameTextView.setText(orderItem.getMenuName());
            priceTextView.setText(String.valueOf(orderItem.getPrice()));
        }
    }

    // 데이터 갱신 메서드
    public void updateOrderList(List<OrderItem> newOrderItemList) {
        orderItemList = newOrderItemList;
        notifyDataSetChanged();
    }
}
