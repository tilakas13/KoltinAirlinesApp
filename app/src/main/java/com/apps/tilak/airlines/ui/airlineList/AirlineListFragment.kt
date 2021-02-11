package com.apps.tilak.airlines.ui.airlineList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.tilak.airlines.R
import com.apps.tilak.airlines.data.model.AirlineItem

class AirlineListFragment : Fragment() {

    companion object {
        fun newInstance() = AirlineListFragment()
    }

    private lateinit var adapterAirlines: AirlinesListAdapter
    private lateinit var viewModel: AirlineListViewModel
    private val airlinesList = ArrayList<AirlineItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.airline_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_airlines)
        adapterAirlines = AirlinesListAdapter(airlinesList)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterAirlines
        prepareAirlinesListItems()
    }

    /**
     * Creating dummy items for listing
     */

    private fun prepareAirlinesListItems() {
        for (i in 1..100) {
            var airlineItem = AirlineItem()
            airlineItem.defaultName = "Mad Max: Fury Road $i"
            airlinesList.add(airlineItem)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}