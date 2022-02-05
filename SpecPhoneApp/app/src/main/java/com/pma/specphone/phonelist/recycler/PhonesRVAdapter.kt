package com.pma.specphone.phonelist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pma.specphone.R
import com.pma.specphone.base.model.PhoneResponseItem

class PhonesRVAdapter (
    private var phones: List<PhoneResponseItem>,

    private val onItemClicked: (Int) -> Unit
    ) : RecyclerView.Adapter<PhonesRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhonesRVViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_phone, parent, false)
    )

    override fun onBindViewHolder(holder: PhonesRVViewHolder, position: Int) {
        holder.bind(phones[position], onItemClicked)
    }

    override fun getItemCount() = phones.size



}