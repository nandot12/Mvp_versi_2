package com.udacoding.udacodingecommerce.ui.keranjang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.udacodingecommerce.BuildConfig
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.keranjang.model.KeranjangItem
import com.udacoding.udacodingecommerce.ui.listProduk.listener.onItemProdukListener
import com.udacoding.udacodingecommerce.util.HeroHelper
import kotlinx.android.synthetic.main.keranjang_item.view.*
import kotlinx.android.synthetic.main.produk_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class KeranjangAdapter(var dataKategori : List<KeranjangItem?>?) : RecyclerView.Adapter<KeranjangAdapter.MyHolderKategori>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderKategori {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.keranjang_item,parent,false)
        return MyHolderKategori(view)
    }

    override fun getItemCount() = dataKategori?.size ?: 0
    override fun onBindViewHolder(holder: MyHolderKategori, position: Int) {
        holder.bind(dataKategori?.get(position))


    }

    class MyHolderKategori(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: KeranjangItem?) {
            itemView.keranjangNama.text = get?.produkNama
            itemView.keranjangPrice.text = "Rp " + HeroHelper.toRupiahFormat(get?.produkHarga ?: "")
            itemView.keranjangQty.text = get?.detailQty



        }

    }
}