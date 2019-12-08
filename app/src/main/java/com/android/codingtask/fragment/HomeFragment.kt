package com.android.codingtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.codingtask.R
import com.android.codingtask.databinding.FragmentHomeBinding
import com.android.codingtask.util.inflateFragment

class HomeFragment:BaseFragment<FragmentHomeBinding>(){
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
            R.layout.fragment_home
        ) as FragmentHomeBinding
        observeClick(root)
        return root
    }



}