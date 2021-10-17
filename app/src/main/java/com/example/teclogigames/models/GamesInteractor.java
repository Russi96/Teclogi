package com.example.teclogigames.models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.teclogigames.entities.GamesItem;
import com.example.teclogigames.module.NetworkModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GamesInteractor {

    private final NetworkModule networkModule = new NetworkModule();
    private final Retrofit retrofit = networkModule.provideRetrofitInstance(networkModule.provideConverterFactory(), networkModule.provideHttpClient());

    public interface OnNetworkResult {
        void Success(List<GamesItem> gamesItems);

        void Error(String message);

        void Empty(String message);

        void Loading();
    }


    public void NetworkResponseDeals(OnNetworkResult onNetworkResult) {

        onNetworkResult.Loading();

        Call<List<GamesItem>> call = networkModule.provideApiService(retrofit).getDeals();
        call.enqueue(new Callback<List<GamesItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<GamesItem>> call, @NonNull Response<List<GamesItem>> response) {
                if (response.message().contains("timeout")) {
                    onNetworkResult.Error("Timeout");
                } else if (response.code() == 402) {
                    onNetworkResult.Error("API Key Limited");
                } else {
                    assert response.body() != null;
                    if (response.body().isEmpty()) {
                        onNetworkResult.Empty("No encontro el juego");
                    } else if (response.isSuccessful()) {
                        List<GamesItem> games = response.body();
                        onNetworkResult.Success(games);
                    } else {
                        onNetworkResult.Error(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GamesItem>> call, @NonNull Throwable t) {
                onNetworkResult.Error(t.getMessage());
            }
        });


    }


    public void NetworkResponseGames(OnNetworkResult onNetworkResult, String title) {

        onNetworkResult.Loading();
        Call<List<GamesItem>> call = networkModule.provideApiService(retrofit).getGames(title);
        call.enqueue(new Callback<List<GamesItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<GamesItem>> call, @NonNull Response<List<GamesItem>> response) {
                if (response.message().contains("timeout")) {
                    onNetworkResult.Error("Timeout");
                } else if (response.code() == 402) {
                    onNetworkResult.Error("API Key Limited");
                } else {
                    assert response.body() != null;
                    if (response.body().isEmpty()) {
                        onNetworkResult.Empty("No encontro el juego");
                    } else if (response.isSuccessful()) {
                        List<GamesItem> games = response.body();
                        onNetworkResult.Success(games);
                    } else {
                        onNetworkResult.Error(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GamesItem>> call, @NonNull Throwable t) {
                onNetworkResult.Error(t.getMessage());
            }
        });


    }

}
