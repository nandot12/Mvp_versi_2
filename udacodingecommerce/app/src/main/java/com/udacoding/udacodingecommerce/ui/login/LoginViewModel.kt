package com.udacoding.udacodingecommerce.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacoding.udacodingecommerce.repo.JualanRepository
import com.udacoding.udacodingecommerce.ui.login.model.LoginResponse
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse

class LoginViewModel : ViewModel() {
    //get repository
        private var jualanRepository = JualanRepository()
        //create live data untuk true /false dari progress bar
        var isLoading = MutableLiveData<Boolean>()

        var login = MutableLiveData<LoginResponse>()

        var apiError = MutableLiveData<Throwable>()

         var isEmpty = MutableLiveData<Boolean>()


    fun login(email : String , password : String){
        if(email.isEmpty() || password.isEmpty()){
            isEmpty.value = true
        }
        else {
            isLoading.value = true

            isEmpty.value = false

            jualanRepository.login(email, password,
                { login.value = it; isLoading.value = false },
                { apiError.value = it; isLoading.value = false })

        }

    }

    override fun onCleared() {
        super.onCleared()
        jualanRepository.onClear()
    }
}