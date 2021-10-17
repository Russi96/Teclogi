package com.example.teclogigames;

import com.example.teclogigames.entities.DetailsGames;
import com.example.teclogigames.entities.GamesItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GamesApi {
    @GET("deals")
    Call<List<GamesItem>> getDeals();

    @GET("games")
    Call<List<GamesItem>> getGames(@Query("title") String title);

    @GET("games")
    Call<DetailsGames> getDetails(@Query("id") String id);

}
