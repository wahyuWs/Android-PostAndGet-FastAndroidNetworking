package com.homework.crudfan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homework.crudfan.databinding.ItemListBinding

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val dataMahasiswa = ArrayList<DataMahasiswa>()

    fun setData(data: List<DataMahasiswa>) {
        dataMahasiswa.clear()
        dataMahasiswa.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(dataMahasiswa[position])
    }

    override fun getItemCount(): Int = dataMahasiswa.size

    inner class ListViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataMahasiswa) {
            with(binding) {
                valueNim.text = data.nim.toString()
                valueName.text = data.name
                valueAge.text = data.age.toString()
            }
        }
    }
}