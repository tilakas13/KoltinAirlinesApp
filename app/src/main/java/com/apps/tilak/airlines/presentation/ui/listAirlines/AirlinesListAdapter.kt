package com.apps.tilak.airlines.presentation.ui.listAirlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apps.tilak.airlines.data.model.AirlineItem
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
        holder.binding.listItemAirlines = itemAirlines
        holder.binding.containerItemAirline.setOnClickListener { itemView ->
            val actionDetailView =
                AirlineListFragmentDirections.actionItemClickToDetailAirlineFragment(itemAirlines)
            itemView.findNavController().navigate(actionDetailView)
        }

    }

    override fun getItemCount(): Int {
        return listAirlines.size
    }

    fun addAirlineItems(airlinesItems: List<AirlineItem>) {
        this.listAirlines = airlinesItems
    }

    inner class AirlinesViewHolder(var binding: ItemListAirlineBinding) :
        RecyclerView.ViewHolder(binding.root)
}