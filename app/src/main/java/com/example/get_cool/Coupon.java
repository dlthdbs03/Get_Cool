package com.example.get_cool;

public class Coupon {
    private String name;
    private int discountValue;
    private CouponType type;

    public Coupon(String name, int discountValue, CouponType type) {
        this.name = name;
        this.discountValue = discountValue;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public CouponType getType() {
        return type;
    }
}