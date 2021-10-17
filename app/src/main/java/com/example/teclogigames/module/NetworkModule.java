package com.example.teclogigames.module;

import static com.example.teclogigames.utils.Constants.BASE_URL;


import com.example.teclogigames.GamesApi;


import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit.RxJavaCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class NetworkModule {


   public OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

   public GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }


   public Retrofit provideRetrofitInstance(
            GsonConverterFactory gsonConverterFactory,
            OkHttpClient okHttpClient
    ) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

   public GamesApi provideApiService(Retrofit retrofit){
        return retrofit.create(GamesApi.class);
    }
}
