package com.udacoding.udacodingecommerce.network

import com.udacoding.udacodingecommerce.ui.keranjang.model.KeranjangResponse
import com.udacoding.udacodingecommerce.ui.listProduk.bottomsheet.model.OrderResponse
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukResponse
import com.udacoding.udacodingecommerce.ui.login.model.LoginResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.ImageResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.JenisProdukResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.KategoriResponse
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {



    @FormUrlEncoded
    @POST("addItemKeranjang")
    fun addItem(@Field("idorder") idOrder : String ,
                @Field("idproduk") idProduk : String ,
                @Field("qty") qty : String ,
                @Field("harga") harga : String
                ):Single<RegisterResponse>

    @FormUrlEncoded
    @POST("order")
    fun order(@Field("iduser") iduser : String ,
              @Field("total") total : String ,
              @Field("idproduk") idproduk : String,
              @Field("qty") qty : String,
              @Field("harga") harga : String) : Single<OrderResponse>


    @GET("populer")
    fun populer():Observable<ProdukResponse>


    @GET("promosi")
    fun promosi():Observable<ProdukResponse>

    @FormUrlEncoded
    @POST("produkPerKategori")
    fun getProduk(@Field("id") id : String) :Observable<ProdukResponse>


    @GET("getKategori")
    fun getKategori():Observable<KategoriResponse>


    @GET("getJenisProduk")
    fun getJenisProduk():Observable<JenisProdukResponse>


    @GET("gambar_slider")
    fun getGambarSlider():Observable<ImageResponse>


    @FormUrlEncoded
    @POST("registerCustomer")
    fun register(@Field("nama") nama : String,
                 @Field("email") email : String ,
                 @Field("hp") hp : String ,
                 @Field("password") password : String):Single<RegisterResponse>



    @FormUrlEncoded
    @POST("loginCustomer")
    fun login(@Field("email") email : String,
              @Field("password") password :String): Single<LoginResponse>


    @FormUrlEncoded
    @POST("getKeranjang")
    fun keranjang(@Field("idOrder") idOrder : String):Observable<KeranjangResponse>


    @FormUrlEncoded
    @POST("UpdateOrder")
    fun checkout(@Field("idOrder") idOrder : String ,
                 @Field("total") total : String ,
                 @Field("note") note :String ) : Single<RegisterResponse>


}