package com.example.teclogigames.models;

import androidx.annotation.NonNull;

import com.example.teclogigames.entities.DetailsGames;
import com.example.teclogigames.entities.GamesItem;
import com.example.teclogigames.module.NetworkModule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsInteractor {
    private final NetworkModule networkModule = new NetworkModule();
    private final Retrofit retrofit = networkModule.provideRetrofitInstance(networkModule.provideConverterFactory(), networkModule.provideHttpClient());

    public interface OnNetworkDetailsResult {

        void SuccessDetails(DetailsGames detailsGames);

        void Error(String message);

        void Loading();
    }

    public void NetworkResponseDetails(OnNetworkDetailsResult onNetworkDetailsResult, String id) {

        onNetworkDetailsResult.Loading();

        Call<DetailsGames> call = networkModule.provideApiService(retrofit).getDetails(id);
        call.enqueue(new Callback<DetailsGames>() {
            @Override
            public void onResponse(@NonNull Call<DetailsGames> call, @NonNull Response<DetailsGames> response) {
                if (response.message().contains("timeout")) {
                    onNetworkDetailsResult.Error("Timeout");
                } else if (response.code() == 402) {
                    onNetworkDetailsResult.Error("API Key Limited");
                } else {
                    assert response.body() != null;
                    if (response.isSuccessful()) {
                        DetailsGames detailsGames = response.body();
                        onNetworkDetailsResult.SuccessDetails(detailsGames);
                    } else {
                        onNetworkDetailsResult.Error(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<DetailsGames> call, @NonNull Throwable t) {
                onNetworkDetailsResult.Error(t.getMessage());
            }
        });
    }
}
