package com.udacoding.udacodingecommerce.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.udacodingecommerce.BuildConfig
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.main.home.listener.onItemClickJenisProduk
import com.udacoding.udacodingecommerce.ui.main.home.model.JpItem
import kotlinx.android.synthetic.main.item_kategori.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class JenisProdukAdapter(var dataKategori : List<JpItem?>?,var itemClick : onItemClickJenisProduk) : RecyclerView.Adapter<JenisProdukAdapter.MyHolderKategori>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderKategori {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_kategori,parent,false)
        return MyHolderKategori(view)
    }

    override fun getItemCount() = dataKategori?.size ?: 0
    override fun onBindViewHolder(holder: MyHolderKategori, position: Int) {
        holder.bind(dataKategori?.get(position))


        holder.itemView.onClick {
            itemClick.onItemClick(dataKategori?.get(position)?.jpNama)
        }
    }

    class MyHolderKategori(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: JpItem?) {
            itemView.kategoriTitle.text = get?.jpNama
            Glide.with(itemView.context).load(BuildConfig.BASE_URL_IMG + get?.jpGambar).into(itemView.kategoriImg)

        }

    }
}