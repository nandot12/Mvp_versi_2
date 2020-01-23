package com.udacoding.udacodingecommerce.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.udacoding.udacodingecommerce.ui.main.MainActivity
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.login.model.LoginResponse
import com.udacoding.udacodingecommerce.ui.register.RegisterActivity
import com.udacoding.udacodingecommerce.util.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel
    private  var sessionManager : SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = CreateViewModel()

        sessionManager = SessionManager(this)

        attachObserve()

        signInLinkRegister.onClick {

            startActivity<RegisterActivity>()
        }


        signInButton.onClick {


            viewModel.login(signUpEmail.text.toString(),
                signPassword.text.toString())

        }
    }

    private fun attachObserve() {

        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.apiError.observe(this, Observer { showError(it) })
        viewModel.login.observe(this, Observer { showResponse(it) })
        viewModel.isEmpty.observe(this, Observer { showIsEmpty(it) })

    }

    private fun showIsEmpty(it: Boolean) {

        if(it) toast("tidak boleh kososng")

    }

    private fun showResponse(it: LoginResponse?) {
        if(it?.status == 200) {
            startActivity<MainActivity>()

            sessionManager?.setIduser(it.user?.userId ?: "")
            sessionManager?.nama = it.user?.userNama ?: ""
            sessionManager?.email = it.user?.userEmail ?: ""
            sessionManager?.phone = it.user?.userHp ?: ""
            sessionManager?.cerateLoginSession("1")

            finish()


        }
        else toast(it?.message ?: "")

    }

    private fun showError(it: Throwable) {
        toast(it.localizedMessage)
    }

    private fun showLoading(show: Boolean?) {

        if(show ?: true) progress.visibility = View.VISIBLE
        else progress.visibility = View.GONE



    }

    fun CreateViewModel() = ViewModelProviders.of(this).get(LoginViewModel::class.java)


}
