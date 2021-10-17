package com.example.teclogigames.entities;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("steamAppID")
    private String steamAppID;
    @SerializedName("thumb")
    private String thumb;
    @SerializedName("title")
    private String title;

    public String getSteamAppID() {
        return steamAppID;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }
}
