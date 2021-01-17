package com.apps.tilak.airlines.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.apps.tilak.airlines.R

class AirlineListFragment : Fragment() {

    companion object {
        fun newInstance() = AirlineListFragment()
    }

    private lateinit var viewModel: AirlineListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.airline_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}