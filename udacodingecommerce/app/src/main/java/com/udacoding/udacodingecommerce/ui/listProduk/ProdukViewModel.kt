package com.udacoding.udacodingecommerce.ui.listProduk

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.udacodingecommerce.repo.JualanRepository
import com.udacoding.udacodingecommerce.ui.listProduk.bottomsheet.model.OrderResponse
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukResponse
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse

class ProdukViewModel : ViewModel() {


    var repo = JualanRepository()
    var produkResponse = MutableLiveData<ProdukResponse>()
    var addItemResponse = MutableLiveData<RegisterResponse>()
    var apiError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    var orderResponse = MutableLiveData<OrderResponse>()


    fun getKategoriProduk(id : String){

        isLoading.value = true
        repo.produkPerKategori(id,{
            produkResponse.value = it ; isLoading.value= false
        },{
            apiError.value = it ; isLoading.value = false
        })


    }
    fun promosi(){

        isLoading.value = true
        repo.produkPromo({
            produkResponse.value = it ; isLoading.value= false
        },{
            apiError.value = it ; isLoading.value = false
        })


    }


    fun order(idUser : String ,total  :String ,harga : String ,idproduk : String ,qty : String ){
        isLoading.value = true
        repo.order(idUser,total,harga,idproduk,qty,{
            orderResponse.value = it ;isLoading.value = false
        },{ apiError.value = it ; isLoading.value= false })
    }


    fun addItem(idOrder : String ,total  :String ,harga : String ,idproduk : String ,qty : String ){
        isLoading.value = true
        repo.addItem(idOrder,total,harga,idproduk,qty,{
            addItemResponse.value = it ;isLoading.value = false
        },{ apiError.value = it ; isLoading.value= false })
    }





}