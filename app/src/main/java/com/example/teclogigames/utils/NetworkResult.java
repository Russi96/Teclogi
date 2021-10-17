package com.example.teclogigames.utils;

import com.example.teclogigames.entities.DetailsGames;
import com.example.teclogigames.entities.GamesItem;

import java.util.List;

public abstract class NetworkResult {

    public List<GamesItem> data;
    public DetailsGames detailsGames;
    public String message;
    public ResponseData responseData;

    public NetworkResult(String message, ResponseData responseData) {
        this.message = message;
        this.responseData = responseData;
    }

    public NetworkResult(ResponseData responseData) {
        this.responseData = responseData;
    }

    public NetworkResult(List<GamesItem> data, ResponseData responseData) {
        this.data = data;
        this.responseData = responseData;
    }

    public NetworkResult(DetailsGames detailsGames, ResponseData responseData) {
        this.detailsGames = detailsGames;
        this.responseData = responseData;
    }


    public static class Success extends NetworkResult {
        public Success(List<GamesItem> data, ResponseData responseData) {
            super(data, responseData);
        }
    }

    public static class SuccessDetail extends NetworkResult {
        public SuccessDetail(DetailsGames detailsGames, ResponseData responseData) {
            super(detailsGames, responseData);
        }
    }

    public static class Error extends NetworkResult {
        public Error(String message, ResponseData responseData) {
            super(message, responseData);
        }
    }

    public static class Loading extends NetworkResult {
        public Loading(ResponseData responseData) {
            super(responseData);
        }
    }


}
