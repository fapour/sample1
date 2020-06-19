package com.example.foodino.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("website")
    private String website;

    @SerializedName("phone")
    private String phone;

    @SerializedName("image")
    private String image;

    @SerializedName("category")
    private int category;

    public Restaurant(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public Restaurant(int id, String name, String address, String website, String phone, String image, int category) {
        this.id = Integer.valueOf(id);
        this.name = name;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.image = image;
        this.category = Integer.valueOf(category);
    }

    public Restaurant(String name, String address, String website, String phone, String image, String category) {
        this.name = name;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.image = image;
        this.category = Integer.valueOf(category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }

    public int getCategory() {
        return category;
    }
}
