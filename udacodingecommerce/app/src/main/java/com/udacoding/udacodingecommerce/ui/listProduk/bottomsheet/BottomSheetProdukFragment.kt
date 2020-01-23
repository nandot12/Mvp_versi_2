package com.udacoding.udacodingecommerce.ui.listProduk.bottomsheet


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.listProduk.ListProdukActivity
import com.udacoding.udacodingecommerce.ui.listProduk.ProdukViewModel
import com.udacoding.udacodingecommerce.ui.listProduk.bottomsheet.model.OrderResponse
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukItem
import com.udacoding.udacodingecommerce.ui.register.model.RegisterResponse
import com.udacoding.udacodingecommerce.util.HeroHelper
import com.udacoding.udacodingecommerce.util.KeranjangManager
import com.udacoding.udacodingecommerce.util.SessionManager
import kotlinx.android.synthetic.main.fragment_bottom_sheet_produk.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class BottomSheetProdukFragment : BottomSheetDialogFragment(){


    private lateinit var produkItem : ProdukItem

    private  lateinit var  sessionManager: SessionManager

    private lateinit var keranjangManager: KeranjangManager

    private lateinit var viewModel: ProdukViewModel

    private var count = 1

    private var total = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_produk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        produkItem = arguments?.getSerializable("produk") as ProdukItem

        viewModel = CreateViewModel()

        sessionManager = SessionManager(context ?: requireContext())
        keranjangManager = KeranjangManager(context ?: requireContext())
        sheetName.text = produkItem.produkNama
        sheetPrice.text = "Rp "+ HeroHelper.toRupiahFormat2(produkItem.produkHarga ?: "")

        sheetKeranjang.text = "Lihat Keranjang : Rp" + HeroHelper.toRupiahFormat2(produkItem.produkHarga ?: "")


        initView()

        attachObserver()
    }

    private fun attachObserver() {

        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.orderResponse.observe(this, Observer { showResponse(it) })
        viewModel.addItemResponse.observe(this, Observer { showaddItemResponse(it) })


    }

    private fun showaddItemResponse(it: RegisterResponse?) {



        if(it?.status == 200) {

            dismiss()

            var total1 = keranjangManager.total
            keranjangManager.total = total1?.toDouble()?.plus(total).toString()
        }

    }

    private fun showResponse(it: OrderResponse?) {
        keranjangManager.idOrder = "${it?.idOrder}"
        keranjangManager.total = "$total"
        keranjangManager.cerateKeranjang("1")
        if(it?.status == 200) dismiss()
        else toast(it?.message ?: "")

    }

    private fun showLoading(show: Boolean?) {

        if(show ?: true) progress.visibility = View.VISIBLE
        else progress.visibility = View.GONE

    }

    private fun CreateViewModel()= ViewModelProviders.of(this).get(ProdukViewModel::class.java)

    private fun initView() {

        sheetMinus.onClick {


            if(count == 1){
                sheetMinus.isEnabled = false
            }
            else {


                count -= 1

                sheetCount.text = "$count"

                updateTotal()
            }

        }

        sheetPlus.onClick {

            if(count == 1){
                sheetMinus.isEnabled = true
            }


            count += 1

            sheetCount.text = "$count"

            updateTotal()
        }



        sheetKeranjang.onClick {


            if(keranjangManager.isKeranjang){

                viewModel.addItem(keranjangManager.idOrder ?: "",""
                    ,produkItem.produkHarga ?: "",produkItem.produkId ?: "","$count")

            }
            else {

                viewModel.order(
                    sessionManager.idUser ?: "",
                    "$total",
                    produkItem.produkHarga ?: "",
                    produkItem.produkId ?: "",
                    "$count"
                )

            }

        }



    }

    private fun updateTotal() {

        total = count.times(produkItem.produkHarga?.toDouble() ?: 0.0)

        sheetKeranjang.isAllCaps = false

        sheetKeranjang.text = "Lihat Keranjang : Rp " + HeroHelper.toRupiahFormat2("${total}")

    }


}
