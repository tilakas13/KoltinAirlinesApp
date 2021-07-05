package com.apps.tilak.airlines.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apps.tilak.airlines.utils.Logger
import com.tilak.apps.airlines.R
import com.tilak.apps.airlines.databinding.SplashFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var logger: Logger

    companion object {
        var TAG = "SplashFragment"
    }

    private var splashScreenBinding: SplashFragmentBinding? = null
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        splashScreenBinding = SplashFragmentBinding.inflate(inflater, container, false)
        return splashScreenBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                logger.printLog(TAG, "Splash time out")
                findNavController().navigate(R.id.action_splash_to_list_airline)
            }
        }, 1500)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        splashScreenBinding = null
    }

}