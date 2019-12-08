package com.android.codingtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.codingtask.R
import com.android.codingtask.base.ViewHolderBinding
import com.android.codingtask.databinding.AdapterRepositoryBinding
import com.android.codingtask.model.Repository
import java.util.ArrayList

class AdapterRepository(
    private val mContext: Context,
    private var arrayList: ArrayList<Repository>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(mContext),
            R.layout.adapter_repository,
            parent,
            false
        )
        return ViewHolderBinding(viewDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repository = arrayList[holder.adapterPosition]
        val adapterDashboardBinding =
            (holder as ViewHolderBinding).binding as AdapterRepositoryBinding

        adapterDashboardBinding.repository = repository
        adapterDashboardBinding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setRepositories(repositories : ArrayList<Repository>){
        arrayList = repositories
        this.notifyDataSetChanged()
    }

}