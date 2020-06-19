package com.example.foodino.models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String userName;

    @SerializedName("image")
    private String profileImage;

    @SerializedName("text")
    private String text;

    @SerializedName("rate")
    private int rate;

    @SerializedName("date")
    private String date;

    public Comment(int id, String userName, String profileImage, String text, int rate, String date) {
        this.id = id;
        this.userName = userName;
        this.profileImage = profileImage;
        this.text = text;
        this.rate = rate;
        this.date = date;
    }

    public Comment(int id, String userName, String text, String date, String profileImage) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.date = date;
        this.profileImage = profileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
