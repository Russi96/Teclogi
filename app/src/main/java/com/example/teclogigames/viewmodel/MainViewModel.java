package com.example.teclogigames.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.teclogigames.entities.DetailsGames;
import com.example.teclogigames.entities.GamesItem;
import com.example.teclogigames.models.DetailsInteractor;
import com.example.teclogigames.models.GamesInteractor;
import com.example.teclogigames.utils.NetworkResult;
import com.example.teclogigames.utils.ResponseData;

import java.util.List;


public class MainViewModel extends AndroidViewModel implements GamesInteractor.OnNetworkResult, DetailsInteractor.OnNetworkDetailsResult {


    GamesInteractor gamesInteractor = new GamesInteractor();
    DetailsInteractor detailsInteractor = new DetailsInteractor();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<NetworkResult> gamesResponse = new MutableLiveData<>();

    public void getDetailsResponse(String id) {
        getDetailsSafeCall(id);
    }

    public void getDealsResponse() {
        getDealsSafeCall();
    }

    public void getGamesResponseSearch(String title) {
        getGamesSafeCall(title);
    }


    private void getDetailsSafeCall(String id) {
        if (hasInternetConnection()) {
            detailsInteractor.NetworkResponseDetails(this, id);
        } else {
            NetworkResult.Error error = new NetworkResult.Error("Sin Conexión a Internet", ResponseData.EMPTY);
            gamesResponse.setValue(error);
        }
    }


    private void getGamesSafeCall(String title) {
        if (hasInternetConnection()) {
            gamesInteractor.NetworkResponseGames(this, title);
        } else {
            NetworkResult.Error error = new NetworkResult.Error("Sin Conexión a Internet", ResponseData.EMPTY);
            gamesResponse.setValue(error);
        }
    }

    private void getDealsSafeCall() {
        if (hasInternetConnection()) {
            gamesInteractor.NetworkResponseDeals(this);
        } else {
            NetworkResult.Error error = new NetworkResult.Error("Sin Conexión a Internet", ResponseData.EMPTY);
            gamesResponse.setValue(error);
        }
    }

    private boolean hasInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = connectivityManager.getActiveNetwork();
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        if (capabilities != null) {
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
        } else {
            return false;
        }
    }

    @Override
    public void Success(List<GamesItem> gamesItems) {
        NetworkResult.Success success = new NetworkResult.Success(gamesItems, ResponseData.SUCCESS);
        gamesResponse.setValue(success);
    }

    @Override
    public void Error(String message) {
        NetworkResult.Error error = new NetworkResult.Error(message, ResponseData.ERROR);
        gamesResponse.setValue(error);
    }

    @Override
    public void Empty(String message) {
        NetworkResult.Error error = new NetworkResult.Error(message, ResponseData.EMPTY);
        gamesResponse.setValue(error);
    }

    @Override
    public void Loading() {
        NetworkResult.Loading loading = new NetworkResult.Loading(ResponseData.LOADING);
        gamesResponse.setValue(loading);

    }


    @Override
    public void SuccessDetails(DetailsGames detailsGames) {
        NetworkResult.SuccessDetail success = new NetworkResult.SuccessDetail(detailsGames, ResponseData.SUCCESS);
        gamesResponse.setValue(success);
    }
}



