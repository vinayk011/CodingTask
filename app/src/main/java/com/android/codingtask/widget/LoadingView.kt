package com.android.codingtask.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.android.codingtask.R
import com.android.codingtask.base.BaseDialog
import com.android.codingtask.databinding.ViewProgressBinding
import com.android.codingtask.util.inflateDialog


class LoadingView(context: Context) :
    BaseDialog<ViewProgressBinding>(context) {

    init {
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }

    public override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = inflateDialog(context, R.layout.view_progress) as ViewProgressBinding
        setContentView(binding.root)
        binding.message = context.getString(R.string.processing)
    }

    fun showWithMessage(message: String): Dialog {
        this.show()
        binding.message = message
        return this
    }
}