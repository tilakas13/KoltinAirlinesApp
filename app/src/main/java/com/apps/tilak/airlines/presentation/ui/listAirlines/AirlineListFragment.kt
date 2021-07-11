package com.apps.tilak.airlines.presentation.ui.listAirlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.tilak.airlines.base.BaseFragment
import com.apps.tilak.airlines.data.model.AirlineItem
import com.apps.tilak.airlines.presentation.common.Logger
import com.apps.tilak.airlines.presentation.ui.utils.Status
import com.tilak.apps.airlines.databinding.AirlineListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AirlineListFragment : BaseFragment() {

    private lateinit var airlineListBinding: AirlineListFragmentBinding


    private val viewModel: AirlineListViewModel by viewModels()
    private val airlinesList = ArrayList<AirlineItem>()

    @Inject
    lateinit var adapterAirlines: AirlinesListAdapter

    @Inject
    lateinit var logger: Logger

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        airlineListBinding = AirlineListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return airlineListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveList(airlinesList)
        val layoutManager = LinearLayoutManager(activity)
        airlineListBinding.recyclerviewAirlines.layoutManager = layoutManager
        airlineListBinding.recyclerviewAirlines.adapter = adapterAirlines
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        viewModel.res.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    logger.printLog(TAG, "data loaded")
                    airlineListBinding.progressBar.visibility = View.GONE
                    it.data?.let { users -> retrieveList(users) }
                }
                Status.ERROR -> {
                    logger.printLog(TAG, "Status.ERROR ")
                    airlineListBinding.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    logger.printLog(TAG, "Status.LOADING")
                    airlineListBinding.progressBar.visibility = View.VISIBLE
                }

            }
        })
        viewModel.getListAirlines()
    }


    private fun retrieveList(listAirlines: List<AirlineItem>) {
        adapterAirlines.apply {
            addAirlineItems(listAirlines)
            notifyDataSetChanged()
        }
    }

    companion object {
        private const val TAG = "AirlineListFragment"
    }
}