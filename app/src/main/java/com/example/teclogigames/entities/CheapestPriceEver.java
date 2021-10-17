package com.example.teclogigames.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheapestPriceEver {
    @SerializedName("date")
    @Expose
    private int date;
    @SerializedName("price")
    @Expose
    private String price;

    public int getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }
}
