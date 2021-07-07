package com.apps.tilak.airlines.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.apps.tilak.airlines.utils.Logger
import com.tilak.apps.airlines.R
import com.tilak.apps.airlines.databinding.FragmentDetailAirlineBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailAirlineFragment : Fragment() {

    private lateinit var binding: FragmentDetailAirlineBinding
    private val navigationArgs: DetailAirlineFragmentArgs by navArgs()

    @Inject
    lateinit var logger: Logger

    companion object {
        const val TAG = "AirlineListFragmentBinding"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_airline, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val airlineItem = navigationArgs.argAirlineItem
        binding.detailAirlines = airlineItem
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.printLog(TAG, "in onDestroy")
    }
}