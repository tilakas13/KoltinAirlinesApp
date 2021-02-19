package com.apps.tilak.airlines.view.airlineList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.apps.tilak.airlines.ViewModelFactory
import com.apps.tilak.airlines.model.AirlineItem
import com.apps.tilak.airlines.network.ApiHelper
import com.apps.tilak.airlines.network.RetrofitBuilder
import com.apps.tilak.airlines.utils.Status
import com.tilak.apps.airlines.R

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
        // viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(AirlineListViewModel::class.java)
        viewModel.getListAirlines().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        //recyclerView.visibility = View.VISIBLE
                        // progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        //recyclerView.visibility = View.VISIBLE
                        // progressBar.visibility = View.GONE
                        // Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        // progressBar.visibility = View.VISIBLE
                        // recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(listAirlines: List<AirlineItem>) {
        adapterAirlines.apply {
            addUsers(listAirlines)
            notifyDataSetChanged()
        }
    }

}