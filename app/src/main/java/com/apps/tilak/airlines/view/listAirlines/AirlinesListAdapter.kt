package com.apps.tilak.airlines.view.listAirlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apps.tilak.airlines.constants.AppConstants
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.utils.Logger
import com.bumptech.glide.Glide
import com.tilak.apps.airlines.R
import com.tilak.apps.airlines.databinding.ItemListAirlineBinding

class AirlinesListAdapter(private var listAirlines: List<AirlineItem>) :
    RecyclerView.Adapter<AirlinesListAdapter.AirlinesViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlinesViewHolder {
        val binding =
            ItemListAirlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirlinesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AirlinesViewHolder, position: Int) {
        val itemAirlines = listAirlines[position]
        Logger.printLog(AppConstants.LOG_TAG, "in onBindViewHolder $itemAirlines.name")
        holder.binding.nameAirlines.text = itemAirlines.defaultName
        Glide.with(holder.binding.imageAirline.context)
            .load(AppConstants.BASE_URL + itemAirlines.logoUrl)
            .centerCrop()
            .placeholder(R.drawable.default_airline)
            .into(holder.binding.imageAirline)
        holder.binding.containerItemAirline.setOnClickListener { itemView ->
            val actionDetailView =
                AirlineListFragmentDirections.actionItemClickToDetailAirlineFragment(itemAirlines);
            itemView.findNavController().navigate(actionDetailView)
        }

    }

    override fun getItemCount(): Int {
        return listAirlines.size
    }

    fun addAirlineItems(airlinesItems: List<AirlineItem>) {
        Logger.printLog(AppConstants.LOG_TAG, "in addAirlineItems")
        this.listAirlines = airlinesItems
    }

    inner class AirlinesViewHolder(var binding: ItemListAirlineBinding) :
        RecyclerView.ViewHolder(binding.root)
}