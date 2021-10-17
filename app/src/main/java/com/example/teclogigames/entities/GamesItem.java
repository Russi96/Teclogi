package com.example.teclogigames.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamesItem implements Parcelable {

    @SerializedName("dealID")
    @Expose
    private String dealID;
    @SerializedName("dealRating")
    @Expose
    private String dealRating;
    @SerializedName("gameID")
    @Expose
    private String gameID;
    @SerializedName("internalName")
    @Expose
    private String internalName;
    @SerializedName("isOnSale")
    @Expose
    private String isOnSale;
    @SerializedName("lastChange")
    @Expose
    private int lastChange;
    @SerializedName("metacriticLink")
    @Expose
    private String metacriticLink;
    @SerializedName("metacriticScore")
    @Expose
    private String metacriticScore;
    @SerializedName("normalPrice")
    @Expose
    private String normalPrice;
    @SerializedName("releaseDate")
    @Expose
    private int releaseDate;
    @SerializedName("salePrice")
    @Expose
    private String salePrice;
    @SerializedName("savings")
    @Expose
    private String savings;
    @SerializedName("steamAppID")
    @Expose
    private String steamAppID;
    @SerializedName("steamRatingCount")
    @Expose
    private String steamRatingCount;
    @SerializedName("steamRatingPercent")
    @Expose
    private String steamRatingPercent;
    @SerializedName("steamRatingText")
    @Expose
    private String steamRatingText;
    @SerializedName("storeID")
    @Expose
    private String storeID;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cheapest")
    @Expose
    private String cheapest;
    @SerializedName("external")
    @Expose
    private String external;

    protected GamesItem(Parcel in) {
        dealID = in.readString();
        dealRating = in.readString();
        gameID = in.readString();
        internalName = in.readString();
        isOnSale = in.readString();
        lastChange = in.readInt();
        metacriticLink = in.readString();
        metacriticScore = in.readString();
        normalPrice = in.readString();
        releaseDate = in.readInt();
        salePrice = in.readString();
        savings = in.readString();
        steamAppID = in.readString();
        steamRatingCount = in.readString();
        steamRatingPercent = in.readString();
        steamRatingText = in.readString();
        storeID = in.readString();
        thumb = in.readString();
        title = in.readString();
        cheapest = in.readString();
        external = in.readString();
    }

    public static final Creator<GamesItem> CREATOR = new Creator<GamesItem>() {
        @Override
        public GamesItem createFromParcel(Parcel in) {
            return new GamesItem(in);
        }

        @Override
        public GamesItem[] newArray(int size) {
            return new GamesItem[size];
        }
    };

    public String getExternal() {
        return external;
    }

    public String getCheapest() {
        return cheapest;
    }

    public String getDealID() {
        return dealID;
    }

    public String getDealRating() {
        return dealRating;
    }

    public String getGameID() {
        return gameID;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getIsOnSale() {
        return isOnSale;
    }

    public int getLastChange() {
        return lastChange;
    }

    public String getMetacriticLink() {
        return metacriticLink;
    }

    public String getMetacriticScore() {
        return metacriticScore;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getSavings() {
        return savings;
    }

    public String getSteamAppID() {
        return steamAppID;
    }

    public String getSteamRatingCount() {
        return steamRatingCount;
    }

    public String getSteamRatingPercent() {
        return steamRatingPercent;
    }

    public String getSteamRatingText() {
        return steamRatingText;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    public GamesItem(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dealID);
        dest.writeString(dealRating);
        dest.writeString(gameID);
        dest.writeString(internalName);
        dest.writeString(isOnSale);
        dest.writeInt(lastChange);
        dest.writeString(metacriticLink);
        dest.writeString(metacriticScore);
        dest.writeString(normalPrice);
        dest.writeInt(releaseDate);
        dest.writeString(salePrice);
        dest.writeString(savings);
        dest.writeString(steamAppID);
        dest.writeString(steamRatingCount);
        dest.writeString(steamRatingPercent);
        dest.writeString(steamRatingText);
        dest.writeString(storeID);
        dest.writeString(thumb);
        dest.writeString(title);
        dest.writeString(cheapest);
        dest.writeString(external);
    }
}
