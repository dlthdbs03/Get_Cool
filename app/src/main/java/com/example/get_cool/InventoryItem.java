package com.example.get_cool;

public class InventoryItem {
    private String name;
    private String quantity;
    private String imageUri;

    public InventoryItem(String name, String quantity, String imageUri) {
        this.name = name;
        this.quantity = quantity;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getImageUri() {
        return imageUri;
    }
}
