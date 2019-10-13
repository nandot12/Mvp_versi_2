package com.udacoding.pegawaiapp.network;

import com.udacoding.pegawaiapp.utils.Constans;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConfig {


    //configurasi interceptor
    public OkHttpClient getInterceptor(){


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return  client;


    }

    //configurasi retrofit

    public Retrofit getRetrofit(){

        return new Retrofit.Builder()
                .client(getInterceptor())
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PegawaiService getService(){

        return getRetrofit().create(PegawaiService.class);
    }
}
