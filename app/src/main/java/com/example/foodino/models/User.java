package com.example.foodino.models;

import com.google.gson.annotations.SerializedName;

public class User {


//    @SerializedName("status")
//    private String status;

        @SerializedName("name")
        private String name;

//    @SerializedName("phone")
//    private String phone;
//
//    @SerializedName("email")
//    private String email;

    public User( String name) {
//        this.status = status;
        this.name = name;
//        this.phone = phone;
//        this.email = email;
    }

        public String getName() {
        return name;
    }

//    public String getPhone() {
//        return phone;
//    }

//    public String getEmail() {
//        return email;
//    }
}
