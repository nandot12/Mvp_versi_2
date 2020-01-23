package com.udacoding.udacodingecommerce.ui.keranjang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.keranjang.adapter.KeranjangAdapter
import com.udacoding.udacodingecommerce.ui.keranjang.model.KeranjangResponse
import com.udacoding.udacodingecommerce.ui.main.MainActivity
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse
import com.udacoding.udacodingecommerce.util.HeroHelper
import com.udacoding.udacodingecommerce.util.KeranjangManager
import kotlinx.android.synthetic.main.activity_keranjang.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class KeranjangActivity : AppCompatActivity() {

    private lateinit var viewModel: KeranjangViewModel
    private lateinit var keranjangManager: KeranjangManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keranjang)


        keranjangManager = KeranjangManager(this)

        viewModel = ViewModelProviders.of(this).get(KeranjangViewModel::class.java)

        viewModel.keranjang(keranjangManager.idOrder ?: "")

        keranjangTotal.text = "Rp "+ HeroHelper.toRupiahFormat2(keranjangManager.total ?: "")


        keranjangCheckout.onClick {
            viewModel.checkout(keranjangManager.idOrder ?: "",
                keranjangManager.total ?: "",
                keranjangNote.text.toString())
        }


        attachObserver()


    }

    private fun attachObserver() {

        viewModel.keranjangResponse.observe(this, Observer { showDataKeranjang(it) })
        viewModel.checkoutResponse.observe(this, Observer { showResponseCheckout(it) })
    }

    private fun showResponseCheckout(it: RegisterResponse?) {

        if(it?.status == 200) {

            keranjangManager.logout()

            alert {
                title = "Informasi"
                message = "Checkout anda berhasil"

                yesButton {
                    startActivity<MainActivity>()
                }
            }.show()
        }

    }

    private fun showDataKeranjang(it: KeranjangResponse) {

        listKeranjanb.adapter = KeranjangAdapter(it.keranjang)


    }
}
