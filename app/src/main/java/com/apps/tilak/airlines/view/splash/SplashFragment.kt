package com.apps.tilak.airlines.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.apps.tilak.airlines.utils.Logger
import com.apps.tilak.airlines.viewmodel.SplashViewModel
import com.tilak.apps.airlines.R
import com.tilak.apps.airlines.databinding.SplashFragmentBinding


class SplashFragment : Fragment() {

    companion object {
        var TAG = "SplashFragment"
    }

    private var splashScreenBinding: SplashFragmentBinding? = null
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        splashScreenBinding = SplashFragmentBinding.inflate(inflater, container, false)
        return splashScreenBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                Logger.printLog(TAG, "Splash time out")
                findNavController().navigate(R.id.action_splash_to_list_airline)
            }
        }, 1500)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        splashScreenBinding = null
    }

}