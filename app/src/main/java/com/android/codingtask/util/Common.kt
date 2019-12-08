package com.android.codingtask.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.GsonBuilder


fun inflateActivity(activity: Activity, layout: Int): ViewDataBinding? {
    return DataBindingUtil.setContentView(activity, layout)
}

fun inflateFragment(
    inflater: LayoutInflater,
    container: ViewGroup?,
    layout: Int
): ViewDataBinding? {
    return DataBindingUtil.inflate(inflater, layout, container, false);
}

fun inflateDialog(context: Context, layout: Int): ViewDataBinding? {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layout, null, false)
}
fun hideKeyboard(activity: Activity?) {
    if (activity != null && activity.window != null) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }
}

val gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    .create()