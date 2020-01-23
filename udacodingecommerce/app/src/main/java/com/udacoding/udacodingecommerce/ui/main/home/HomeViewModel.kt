package com.udacoding.udacodingecommerce.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.udacoding.udacodingecommerce.repo.JualanRepository
import com.udacoding.udacodingecommerce.ui.main.home.model.ImageResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.JenisProdukResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.KategoriResponse

class HomeViewModel : ViewModel() {

    var repo = JualanRepository()
    var gambar = MutableLiveData<ImageResponse>()

    var kategoriResponse = MutableLiveData<KategoriResponse>()
    var jenisProdukResponse = MutableLiveData<JenisProdukResponse>()

    var isLoading = MutableLiveData<Boolean>()

    var apiError = MutableLiveData<Throwable>()


    fun getGambar(){
        isLoading.value = true

        repo.getGambar({
            gambar.value = it ; isLoading.value = false
        },{
            apiError.value = it ; isLoading.value = false
        })
    }

    fun getKategori(){

        isLoading.value = true
        repo.kategori({kategoriResponse.value = it ; isLoading.value = false}
            ,{apiError.value = it; isLoading.value = false})


    } fun getJenisProduk(){

        isLoading.value = true
        repo.jenisProduk({jenisProdukResponse.value = it ; isLoading.value = false}
            ,{apiError.value = it; isLoading.value = false})


    }
}