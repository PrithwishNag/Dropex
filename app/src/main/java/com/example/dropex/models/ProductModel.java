package com.example.dropex.models;

public class ProductModel {

    private int id;
    private String name;
    private String description;
    private int price;
    private int imgId;

    public ProductModel(String name, String description, int price, int imgId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
