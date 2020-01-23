package com.udacoding.udacodingecommerce.ui.keranjang

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.udacodingecommerce.repo.JualanRepository
import com.udacoding.udacodingecommerce.ui.keranjang.model.KeranjangResponse
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse

class KeranjangViewModel  : ViewModel(){


    var repo = JualanRepository()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var keranjangResponse = MutableLiveData<KeranjangResponse>()
    var checkoutResponse = MutableLiveData<RegisterResponse>()



    fun keranjang(idOrder : String ){

        isLoading.value = true
        repo.keranjang(idOrder,{
            keranjangResponse.value = it ;isLoading.value = false
        },{apiError.value = it ; isLoading.value = false})

    }

    fun checkout (idOrder : String , total : String , note : String){

        repo.checkout(idOrder,total,note,{

            checkoutResponse.value = it
        },{
            apiError.value = it
        })
    }






}