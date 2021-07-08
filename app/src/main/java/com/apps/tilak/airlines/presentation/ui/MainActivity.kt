package com.apps.tilak.airlines.presentation.ui

import android.os.Bundle
import com.apps.tilak.airlines.base.BaseActivity
import com.tilak.apps.airlines.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}