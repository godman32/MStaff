package com.gm.mstaff.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gm.mstaff.databinding.ItemDataBinding
import com.gm.mstaff.utills.constant.Status
import com.gm.mstaff.utills.ext.format
import com.gm.mstaff.utills.listener.OnScrollFullListener

/**
 * Created by @godman on 21/06/23.
 */

class DataAdapter(
    private val onScrollFullListener: OnScrollFullListener
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private var staffs = ArrayList<Staff>()

    var status= Status.FREE


    fun add(articles: List<Staff>) {
        this.staffs.addAll(articles)
        status= Status.FREE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData(){
        staffs.clear()
        notifyDataSetChanged()
        status= Status.FREE
    }

    class ViewHolder(val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nik.text= staffs[position].nik
        holder.binding.name.text= staffs[position].nama
        holder.binding.prov.text= staffs[position].namaProp
        holder.binding.value.text= staffs[position].jumlahPenduduk?.toDouble()?.format()+" Jiwa"


        if(position > itemCount - 8 && status == Status.FREE && itemCount > 0){
            status= Status.LOAD
            onScrollFullListener.onScrollFull()
        }
    }

    override fun getItemCount(): Int {
        return staffs.size
    }

}