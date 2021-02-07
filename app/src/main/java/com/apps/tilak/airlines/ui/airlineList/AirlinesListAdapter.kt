package com.apps.tilak.airlines.ui.airlineList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.apps.tilak.airlines.R
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.data.model.AirlineModel

public class AirlinesListAdapter(private var listAirlines: List<AirlineItem>) :
    RecyclerView.Adapter<AirlinesListAdapter.AirlinesViewHolder>() {

    inner class AirlinesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.name_airlines)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlinesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_airline, parent, false)
        return AirlinesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AirlinesViewHolder, position: Int) {
        val itemAirlines = listAirlines[position]
        holder.title.text = itemAirlines.defaultName
    }

    override fun getItemCount(): Int {
        return listAirlines.size
    }
}