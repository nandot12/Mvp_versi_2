package com.udacoding.udacodingecommerce.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.udacodingecommerce.repo.JualanRepository
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse

class RegisterViewModel : ViewModel() {


    //get repository
    private var jualanRepository = JualanRepository()
    //create live data untuk true /false dari progress bar
   var isLoading = MutableLiveData<Boolean>()

    var responseRegister = MutableLiveData<RegisterResponse>()

    var apiError = MutableLiveData<Throwable>()



    fun register(name : String ,email : String ,password : String,
                 hp : String){
        isLoading.value = true
        jualanRepository.register(name,email,password,hp,
            {
                responseRegister.value = it ;isLoading.value = false
            },{ apiError.value = it ;isLoading.value = false
            })
    }


    override fun onCleared() {
        super.onCleared()

        jualanRepository.onClear()
    }

}
