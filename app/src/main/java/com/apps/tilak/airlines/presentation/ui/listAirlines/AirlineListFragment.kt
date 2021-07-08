package com.apps.tilak.airlines.presentation.ui.listAirlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.tilak.airlines.base.BaseFragment
import com.apps.tilak.airlines.common.AppConstants.LOG_TAG
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.data.network.ApiHelper
import com.apps.tilak.airlines.data.network.RetrofitBuilder
import com.apps.tilak.airlines.data.repository.AirlinesRepository
import com.apps.tilak.airlines.presentation.common.Logger
import com.apps.tilak.airlines.presentation.ui.utils.Status
import com.tilak.apps.airlines.databinding.AirlineListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AirlineListFragment : BaseFragment() {

    private lateinit var airlineListBinding: AirlineListFragmentBinding
    private lateinit var adapterAirlines: AirlinesListAdapter
    private val viewModel: AirlineListViewModel by viewModels()
    private val airlinesList = ArrayList<AirlineItem>()

    @Inject
    lateinit var logger: Logger

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
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        // viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        viewModel.setRepository(AirlinesRepository(ApiHelper(RetrofitBuilder.apiService)))

        viewModel.getListAirlines().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        logger.printLog(LOG_TAG, "data loaded")
                        airlineListBinding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        logger.printLog(LOG_TAG, "Status.ERROR ")
                        airlineListBinding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        logger.printLog(LOG_TAG, "Status.LOADING")
                        airlineListBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }




    private fun retrieveList(listAirlines: List<AirlineItem>) {
        adapterAirlines.apply {
            addAirlineItems(listAirlines)
            notifyDataSetChanged()
        }
    }

}