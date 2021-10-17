package com.example.teclogigames.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsGames  {
    @SerializedName("cheapestPriceEver")
    private CheapestPriceEver cheapestPriceEver;
    @SerializedName("deals")
    private List<Deal> deals;
    @SerializedName("info")
    private Info info;

    public CheapestPriceEver getCheapestPriceEver() {
        return cheapestPriceEver;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public Info getInfo() {
        return info;
    }
}
