package com.pma.specphone.phonelist.recycler



import android.icu.number.NumberFormatter.with
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pma.specphone.base.model.PhoneResponseItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_phone.view.*

class PhonesRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(phone: PhoneResponseItem, onItemClicked: (Int) -> Unit) {

        itemView.phoneBrandTxt.text = phone.brand + " " + phone.model
        itemView.phoneDetailsTxt.text = phone.announced + " " + phone.dimentions + " " + phone.internalMemory + " " + phone.rAM

        Picasso.get().load(phone.img_url).into(itemView.phoneImg)

        itemView.setOnClickListener { onItemClicked.invoke(phone.id) }
    }
}