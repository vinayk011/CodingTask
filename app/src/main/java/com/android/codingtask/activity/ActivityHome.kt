package com.android.codingtask.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.android.codingtask.R
import com.android.codingtask.databinding.ActivityHomeBinding
import com.android.codingtask.util.inflateActivity
import kotlinx.android.synthetic.main.activity_home.*

class ActivityHome : BaseActivity<ActivityHomeBinding>() {

    private val navHostFragment: NavHostFragment by lazy {
        home_nav_fragment as NavHostFragment
    }
    private val navInflater by lazy {
        navHostFragment.navController.navInflater
    }
    @Suppress("unused")
    private val navGraph by lazy { navInflater.inflate(R.navigation.main_view) }


    private var selected: Int = R.id.home_fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            inflateActivity(this, R.layout.activity_home) as ActivityHomeBinding
        init()
    }

    private fun init() {
        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            if (it.itemId != selected) {
                selected = it.itemId
                navHostFragment.navController.navigate(it.itemId)
                return@setOnNavigationItemSelectedListener true
            }
            return@setOnNavigationItemSelectedListener false
        }
    }
}