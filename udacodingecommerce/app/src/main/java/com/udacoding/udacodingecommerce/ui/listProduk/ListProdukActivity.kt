package com.udacoding.udacodingecommerce.ui.listProduk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.keranjang.KeranjangActivity
import com.udacoding.udacodingecommerce.ui.listProduk.adapter.ProdukAdapter
import com.udacoding.udacodingecommerce.ui.listProduk.bottomsheet.BottomSheetProdukFragment
import com.udacoding.udacodingecommerce.ui.listProduk.listener.onItemProdukListener
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukItem
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukResponse
import com.udacoding.udacodingecommerce.util.KeranjangManager
import kotlinx.android.synthetic.main.activity_list_produk.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ListProdukActivity : AppCompatActivity() {

    lateinit var viewModel: ProdukViewModel
    private var id : String? = null
    private lateinit var keranjangManager: KeranjangManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produk)

        keranjangManager = KeranjangManager(this)



        if (keranjangManager.isKeranjang){


            buttonKeranjang.visibility = View.VISIBLE
        }

        buttonKeranjang.onClick {
            startActivity<KeranjangActivity>()
        }



        viewModel = ViewModelProviders.of(this).get(ProdukViewModel::class.java)
        id = intent.getStringExtra("id")

        if(id != null) {

            viewModel.getKategoriProduk(id ?: "")
        }
        else{

            var name = intent.getStringExtra("name")

            if(name == "PROMOSI"){
                viewModel.promosi()
            }
            else if(name == "POPULER"){



            }
        }





        attachObserver()
    }

    private fun attachObserver() {
        
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.apiError.observe(this, Observer { showError(it) })
        viewModel.produkResponse.observe(this, Observer { showProduk(it) })
        
    }

    private fun showProduk(it: ProdukResponse?) {

        listProduk.adapter = ProdukAdapter(it?.produk,object  : onItemProdukListener{
            override fun item(produk: ProdukItem?) {

                var bundle= Bundle()
                bundle.putSerializable("produk",produk)


                var bottomSheetDialogFragment = BottomSheetProdukFragment()
                bottomSheetDialogFragment.arguments = bundle
                bottomSheetDialogFragment.show(supportFragmentManager,"produk")


            }
        })

    }

    private fun showError(it: Throwable?) {

        toast(it?.message ?: "")

    }

    private fun showLoading(show: Boolean?) {

        if(show ?: true)progressProduk.visibility = View.VISIBLE
        else progressProduk.visibility = View.GONE

    }
}
