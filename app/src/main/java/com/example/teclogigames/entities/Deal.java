package com.example.teclogigames.entities;

import com.google.gson.annotations.SerializedName;

public class Deal {
    @SerializedName("dealID")
    private String dealID;
    @SerializedName("price")
    private String price;
    @SerializedName("retailPrice")
    private String retailPrice;
    @SerializedName("savings")
    private String savings;
    @SerializedName("storeID")
    private String storeID;

    public String getDealID() {
        return dealID;
    }

    public String getPrice() {
        return price;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public String getSavings() {
        return savings;
    }

    public String getStoreID() {
        return storeID;
    }
}
