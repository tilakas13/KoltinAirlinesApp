package com.apps.tilak.airlines.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.tilak.airlines.utils.Logger
import com.tilak.apps.airlines.R


class DetailAirlineFragment : Fragment() {

    companion object {
        var TAG = "AirlineListFragmentBinding"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_airline, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.printLog(TAG, "in onDestroy")
    }

}