package com.android.codingtask.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.android.codingtask.activity.ActivityHome

fun home(context: Context, bundle: Bundle?, finish: Boolean = false) {
    val intent = Intent(context, ActivityHome::class.java)
    bundle?.let { intent.putExtras(it) }
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    if (finish) {
        (context as Activity).finish()
    }
}