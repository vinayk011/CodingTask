package com.android.codingtask.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.codingtask.R
import com.android.codingtask.adapter.AdapterRepository
import com.android.codingtask.base.BaseFragment
import com.android.codingtask.databinding.FragmentHomeBinding
import com.android.codingtask.model.Repository
import com.android.codingtask.network.model.NetworkResponse
import com.android.codingtask.network_call.FetchGitReposNetworkCall
import com.android.codingtask.util.inflateFragment
import com.android.codingtask.widget.LoadingView

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val networkCall by lazy {
        ViewModelProviders.of(this).get(FetchGitReposNetworkCall::class.java)
    }
    private var networkLoader: LoadingView? = null

    private var repositoryList = ArrayList<Repository>()
    private val adapterRepository by lazy {
        context?.let { AdapterRepository(it, repositoryList) }
    }

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

    override fun init() {
        networkCall.getResult().observe(this, networkResult)
        binding.recyclerView.adapter = adapterRepository
        binding.hasRepos = repositoryList.isNotEmpty()
    }

    override fun resume() {
        if (networkLoader?.isShowing == true) {
            networkLoader?.dismiss()
        }
        context?.let {
            networkLoader = LoadingView(it)
            networkLoader?.let { nl ->
                addDialog(nl)
                nl.show()
            }
            networkCall.execute(0)
        }
    }

    private val networkResult = Observer<NetworkResponse<ArrayList<Repository>>> { res ->
        networkLoader?.dismiss()
        when (res) {
            is NetworkResponse.Success -> {
                res.response?.let {
                    repositoryList = it
                    adapterRepository?.setRepositories(repositoryList)
                    binding.hasRepos = repositoryList.isNotEmpty()
                }
            }
            is NetworkResponse.Error -> {
                if (res.error.isNotEmpty()) {
                    showSnackBar(res.error[0].asString())
                }
            }
            is NetworkResponse.Exception -> {
                showSnackBar(getString(R.string.something_went_wrong))
            }
        }
    }

}