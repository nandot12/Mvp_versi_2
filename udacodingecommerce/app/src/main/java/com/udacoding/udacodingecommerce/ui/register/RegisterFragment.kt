package com.udacoding.udacodingecommerce.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.udacoding.udacodingecommerce.R.layout.register_fragment
import com.udacoding.udacodingecommerce.ui.login.LoginActivity
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse
import kotlinx.android.synthetic.main.register_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        signUpButton.onClick {
            viewModel.register(signUpNama.text.toString(),
                signUpEmail.text.toString(),
                signPassword.text.toString(),
                signHp.text.toString())
        }
        attachObserve()
    }

    private fun attachObserve() {

        viewModel.isLoading.observe(this, Observer { showProgress(it) })

        viewModel.apiError.observe(this, Observer { showError(it) })

        viewModel.responseRegister.observe(this, Observer { showResponse(it) })

    }

    private fun showResponse(it: RegisterResponse?) {



        if(it?.status == 200) activity?.startActivity<LoginActivity>()
        else activity?.toast(it?.message ?: "")

    }

    private fun showError(it: Throwable?) {
        activity?.toast(it?.message ?: "")

    }

    private fun showProgress(it: Boolean?) {

        if(it != false){

            progress.visibility = View.VISIBLE
        }
        else{
            progress.visibility = View.GONE
        }

    }

}
