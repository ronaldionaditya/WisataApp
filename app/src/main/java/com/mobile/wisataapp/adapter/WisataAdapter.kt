package com.mobile.wisataapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.wisataapp.R
import com.mobile.wisataapp.detail.DetailWisataActivity
import com.mobile.wisataapp.model.Wisata

class WisataAdapter(val data: List<Wisata>?) : RecyclerView.Adapter<WisataAdapter.WisataHolder>() {
    class WisataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val img = itemView.findViewById<ImageView>(R.id.itemImg)
        val textName = itemView.findViewById<TextView>(R.id.itemNamaLokasi)
        val itemNamaTempat = itemView.findViewById<TextView>(R.id.itemNamaTempat)
        //memanggil view


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)

        val holder = WisataHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: WisataHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(data?.get(position)?.gambar)
            .into(holder.img)

        holder.itemNamaTempat.text = data?.get(position)?.nama_tempat
        holder.textName.text = data?.get(position)?.lokasi

        holder.itemView.setOnClickListener {

            //action click
            val intent = Intent(holder.itemView.context, DetailWisataActivity::class.java)
            intent.putExtra("jdl", data?.get(position)?.nama_tempat)
            intent.putExtra("img", data?.get(position)?.gambar)
            intent.putExtra("desk", data?.get(position)?.deskripsi)
    
            holder.itemView.context.startActivity(intent)
            
        }


    }

    override fun getItemCount(): Int {

        return data?.size ?: 0

    }
}