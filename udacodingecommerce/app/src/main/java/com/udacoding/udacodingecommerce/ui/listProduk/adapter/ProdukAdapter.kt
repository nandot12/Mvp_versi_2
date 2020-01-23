package com.udacoding.udacodingecommerce.ui.listProduk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.udacodingecommerce.BuildConfig
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.listProduk.listener.onItemProdukListener
import com.udacoding.udacodingecommerce.ui.listProduk.model.ProdukItem
import com.udacoding.udacodingecommerce.ui.main.home.listener.onItemClickJenisProduk
import com.udacoding.udacodingecommerce.util.HeroHelper
import kotlinx.android.synthetic.main.item_kategori.view.*
import kotlinx.android.synthetic.main.produk_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ProdukAdapter(var dataKategori : List<ProdukItem?>?,var itemClick : onItemProdukListener) : RecyclerView.Adapter<ProdukAdapter.MyHolderKategori>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderKategori {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.produk_item,parent,false)
        return MyHolderKategori(view)
    }

    override fun getItemCount() = dataKategori?.size ?: 0
    override fun onBindViewHolder(holder: MyHolderKategori, position: Int) {
        holder.bind(dataKategori?.get(position))

        holder.itemView.onClick {
            itemClick.item(dataKategori?.get(position))
        }
    }

    class MyHolderKategori(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ProdukItem?) {
            itemView.produkItemTitle.text = get?.produkNama
            Glide.with(itemView.context).load(BuildConfig.BASE_URL_IMG + get?.produkGambar).into(itemView.produkItemImg)
            itemView.produkItemPrice.text = "Rp " + HeroHelper.toRupiahFormat(get?.produkHarga ?: "")
            itemView.produkItemRating.rating = get?.produkRating?.toFloat() ?: 0f



        }

    }
}