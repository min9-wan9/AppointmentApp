package com.example.appointmentapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.appointmentapp.Activity.DetailActivity
import com.example.appointmentapp.Domain.DoctorModel
import com.example.appointmentapp.databinding.ViewholderTopDoctorBinding

class TopDoctorAdapter(val items: MutableList<DoctorModel>) :
    RecyclerView.Adapter<TopDoctorAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderTopDoctorBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDoctorAdapter.ViewHolder {
        context = parent.context
        val binding =
            ViewholderTopDoctorBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TopDoctorAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.nameTxt.text = item.Name
        holder.binding.scoreTxt.text = item.Rating.toString()
        holder.binding.specialTxt.text = item.Special
        holder.binding.yearTxt.text = item.Expriense.toString() + " Year"

        Glide.with(context)
            .load(item.Picture)
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.img)

        holder.itemView.setOnClickListener {
            var intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context?.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return items.count()
    }
}