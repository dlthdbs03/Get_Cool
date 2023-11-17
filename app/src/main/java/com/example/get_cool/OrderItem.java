package com.example.get_cool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderItem {
    private String orderDate;
    private String menuName;
    private String price;

    public OrderItem(String menuName, String price) {
        this.orderDate = getCurrentDate();
        this.menuName = menuName;
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    // 현재 날짜를 "yy.MM.dd" 형식으로 반환하는 메서드
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy.MM.dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public String getMenuName() {
        return menuName;
    }

    public String getPrice() {
        return price;
    }
}
