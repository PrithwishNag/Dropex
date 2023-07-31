package com.example.dropex.models;

import com.example.dropex.enums.Size;

public class CartModel {

    private int id;
    private int quantity;
    private Size size;
    private int userId;
    private int productId;

    public CartModel(int id, int quantity, Size size, int userId, int productId) {
        this.id = id;
        this.quantity = quantity;
        this.size = size;
        this.userId = userId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
