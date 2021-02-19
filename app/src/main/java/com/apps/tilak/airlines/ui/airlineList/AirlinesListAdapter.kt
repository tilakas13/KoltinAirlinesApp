package com.apps.tilak.airlines.ui.airlineList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.apps.tilak.airlines.R
import com.apps.tilak.airlines.constants.AppConstants
import com.apps.tilak.airlines.model.AirlineItem
import com.apps.tilak.airlines.utils.Logger
import com.bumptech.glide.Glide

public class AirlinesListAdapter(private var listAirlines: List<AirlineItem>) :
    RecyclerView.Adapter<AirlinesListAdapter.AirlinesViewHolder>() {

    var TAG: String = "AirlinesListAdapter"

    inner class AirlinesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.name_airlines)
        var image: ImageView = view.findViewById(R.id.image_airline)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirlinesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_airline, parent, false)
        return AirlinesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AirlinesViewHolder, position: Int) {
        val itemAirlines = listAirlines[position]
        Logger.printLog(TAG,itemAirlines.logoUrl)
        holder.title.text = itemAirlines.defaultName
        Glide
            .with(holder.image.context)
            .load(AppConstants.BASE_URL + itemAirlines.logoUrl)
            .centerCrop()
            .placeholder(R.drawable.default_airline)
            .into(holder.image);
    }

    override fun getItemCount(): Int {
        return listAirlines.size
    }

    fun addUsers(airlinesItems: List<AirlineItem>) {
        this.listAirlines = airlinesItems

    }
}