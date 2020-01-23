package com.udacoding.udacodingecommerce.network

import com.udacoding.udacodingecommerce.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {




    //configurasi interceptor
    fun getInterceptor():OkHttpClient{

        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        var okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

        return okHttpClient

    }


    //configurasi retrofit
    fun getRetrofit():Retrofit{

        var retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return  retrofit
    }

    fun getService() = getRetrofit().create(ApiService::class.java)

//
//    fun getRequest():ApiService {
//
//        return getRetrofit().create(ApiService::class.java)
//    }
}