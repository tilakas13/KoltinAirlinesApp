package com.apps.tilak.airlines.view.listAirlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.network.ApiHelper
import com.apps.tilak.airlines.network.RetrofitBuilder
import com.apps.tilak.airlines.data.repository.AirlinesRepository
import com.apps.tilak.airlines.utils.Logger
import com.apps.tilak.airlines.utils.Status
import com.apps.tilak.airlines.viewmodel.AirlineListViewModel
import com.tilak.apps.airlines.databinding.AirlineListFragmentBinding

class AirlineListFragment : Fragment() {

    companion object {
        const val TAG = "AirlineListFragment"
    }

    private lateinit var airlineListBinding: AirlineListFragmentBinding
    private lateinit var adapterAirlines: AirlinesListAdapter
    private lateinit var viewModel: AirlineListViewModel
    private val airlinesList = ArrayList<AirlineItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        airlineListBinding = AirlineListFragmentBinding.inflate(inflater, container, false)
        return airlineListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterAirlines = AirlinesListAdapter(airlinesList)
        val layoutManager = LinearLayoutManager(activity)
        airlineListBinding.recyclerviewAirlines.layoutManager = layoutManager
        airlineListBinding.recyclerviewAirlines.adapter = adapterAirlines
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
                        airlineListBinding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        Logger.printLog(TAG, "Status.ERROR ")
                        airlineListBinding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        Logger.printLog(TAG, "Status.LOADING")
                        airlineListBinding.progressBar.visibility = View.VISIBLE
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