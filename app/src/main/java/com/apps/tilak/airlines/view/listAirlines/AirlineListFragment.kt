package com.apps.tilak.airlines.view.listAirlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.tilak.airlines.model.AirlineItem
import com.apps.tilak.airlines.network.ApiHelper
import com.apps.tilak.airlines.network.RetrofitBuilder
import com.apps.tilak.airlines.repository.AirlinesRepository
import com.apps.tilak.airlines.utils.Logger
import com.apps.tilak.airlines.utils.Status
import com.tilak.apps.airlines.R

class AirlineListFragment : Fragment() {
    companion object {
        const val TAG = "AirlineListFragment"
    }

    private lateinit var progressBarLoading: ProgressBar;

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
        progressBarLoading = view.findViewById(R.id.progressBar);
        adapterAirlines = AirlinesListAdapter(airlinesList)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterAirlines
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        viewModel.setRepository(AirlinesRepository(ApiHelper(RetrofitBuilder.apiService)))

        viewModel.getListAirlines().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Logger.printLog(TAG, "data loaded")
                        progressBarLoading.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        Logger.printLog(TAG, "Status.ERROR ")
                        progressBarLoading.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        Logger.printLog(TAG, "Status.LOADING")
                        progressBarLoading.visibility = View.VISIBLE
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