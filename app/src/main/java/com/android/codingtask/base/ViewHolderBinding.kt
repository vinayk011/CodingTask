package com.android.codingtask.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView



open class ViewHolderBinding(open var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)