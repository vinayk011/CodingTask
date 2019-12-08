package com.android.codingtask.activity

import android.os.Bundle
import com.android.codingtask.R
import com.android.codingtask.base.BaseActivity
import com.android.codingtask.databinding.ActivitySplashScreenBinding
import com.android.codingtask.util.home
import com.android.codingtask.util.inflateActivity

class ActivitySplashScreen: BaseActivity<ActivitySplashScreenBinding>(){
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding =
            inflateActivity(this, R.layout.activity_splash_screen) as ActivitySplashScreenBinding
        init()
    }

    private fun init(){
        home(this, null, false)
    }
}