package com.udacoding.udacodingecommerce.ui.main.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.main.home.model.ImageResponse
import com.synnapps.carouselview.ImageListener
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.udacoding.udacodingecommerce.BuildConfig
import com.udacoding.udacodingecommerce.ui.listProduk.ListProdukActivity
import com.udacoding.udacodingecommerce.ui.main.home.adapter.JenisProdukAdapter
import com.udacoding.udacodingecommerce.ui.main.home.adapter.KategoriAdapter
import com.udacoding.udacodingecommerce.ui.main.home.listener.onItemClick
import com.udacoding.udacodingecommerce.ui.main.home.listener.onItemClickJenisProduk
import com.udacoding.udacodingecommerce.ui.main.home.model.DataItem
import com.udacoding.udacodingecommerce.ui.main.home.model.JenisProdukResponse
import com.udacoding.udacodingecommerce.ui.main.home.model.KategoriResponse
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var data : List<DataItem?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.udacoding.udacodingecommerce.R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




        viewModel = createViewModel()
        viewModel.getGambar()

        viewModel.getKategori()
        viewModel.getJenisProduk()

        attachObsever()




    }

    private fun attachObsever() {

        viewModel.gambar.observe(this, Observer { showImage(it) })
        viewModel.kategoriResponse.observe(this, Observer { showDataKategori(it) })
        viewModel.jenisProdukResponse.observe(this, Observer { showJenisProduk(it) })

    }

    private fun showJenisProduk(it: JenisProdukResponse?) {
        listJenisProduk.adapter = JenisProdukAdapter(it?.jp,object :onItemClickJenisProduk{
            override fun onItemClick(name: String?) {

                startActivity<ListProdukActivity>("name" to name)
            }

        })

    }

    private fun showDataKategori(it: KategoriResponse?) {


        listKategori.adapter = KategoriAdapter(it?.kategori,object : onItemClick{
            override fun onItem(id: String) {
                startActivity<ListProdukActivity>("id" to id)
            }

        })



    }

    private fun showImage(it: ImageResponse?) {

         data = it?.data

        carouselView.setImageListener(imageListener)
        carouselView.pageCount = data?.size ?: 0

    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            context?.let {
                Glide.with(it).load(BuildConfig.BASE_URL_IMG + data?.get(position)?.produkGambar)
                    .into(imageView)


            }
        }
    }

    private fun createViewModel() = ViewModelProviders.of(this).get(HomeViewModel::class.java)



}
