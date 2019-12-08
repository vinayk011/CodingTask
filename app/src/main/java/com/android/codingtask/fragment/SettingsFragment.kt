package com.android.codingtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.codingtask.R
import com.android.codingtask.databinding.FragmentSettingsBinding
import com.android.codingtask.util.inflateFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateFragment(
            inflater,
            container,
            R.layout.fragment_settings
        ) as FragmentSettingsBinding
        observeClick(root)
        return root
    }
}