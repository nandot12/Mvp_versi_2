package com.udacoding.udacodingecommerce.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.udacodingecommerce.BuildConfig
import com.udacoding.udacodingecommerce.R
import com.udacoding.udacodingecommerce.ui.main.home.listener.onItemClick
import com.udacoding.udacodingecommerce.ui.main.home.model.KategoriItem
import kotlinx.android.synthetic.main.item_kategori.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class KategoriAdapter(var dataKategori : List<KategoriItem?>?, var itemClick : onItemClick) : RecyclerView.Adapter<KategoriAdapter.MyHolderKategori>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderKategori {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_kategori,parent,false)
        return MyHolderKategori(view)
    }

    override fun getItemCount() = dataKategori?.size ?: 0
    override fun onBindViewHolder(holder: MyHolderKategori, position: Int) {
        holder.bind(dataKategori?.get(position))
        holder.itemView.onClick {
            itemClick.onItem(dataKategori?.get(position)?.kategoriId ?: "")
        }
    }

    class MyHolderKategori(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: KategoriItem?) {
            itemView.kategoriTitle.text = get?.kategoriNama
            Glide.with(itemView.context).load(BuildConfig.BASE_URL_IMG + get?.foto).into(itemView.kategoriImg)

        }

    }
}