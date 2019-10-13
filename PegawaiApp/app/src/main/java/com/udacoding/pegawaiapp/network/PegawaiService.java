package com.udacoding.pegawaiapp.network;

import com.udacoding.pegawaiapp.home.data.ResultUser;
import com.udacoding.pegawaiapp.login.data.ResultLogin;
import com.udacoding.pegawaiapp.register.data.ResultRegister;
import com.udacoding.pegawaiapp.update.data.ResultUpdate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PegawaiService {


    @FormUrlEncoded
    @POST("update")
    Call<ResultUpdate> updateUser(@Field("id") String id ,
                                  @Field("name") String name ,
                                  @Field("email") String email) ;


    @FormUrlEncoded
    @POST("delete")
    Call<ResultUpdate> hapusUser(@Field("id") String id ) ;

    @GET("getUser")
    Call<ResultUser> getUser();

    @FormUrlEncoded
    @POST("register")
    Call<ResultRegister> register(@Field("name") String name ,
                                  @Field("email") String email ,
                                  @Field("password")String pass);


    @FormUrlEncoded
    @POST("login")
    Call<ResultLogin> login (@Field("email") String email ,
                             @Field("password") String password );
}
