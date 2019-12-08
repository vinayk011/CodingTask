package com.android.codingtask.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.android.codingtask.util.hideKeyboard
import java.util.*

open class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    private val dialogs = HashSet<Dialog>()
    private val observers = HashSet<LifecycleObserver>()

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        newIntent(intent)
    }

    open fun newIntent(intent: Intent?) {}

    override fun onResume() {
        super.onResume()
        hideKeyboard(this)
        resume()
    }

    open fun resume() {}

    override fun onPause() {
        hideKeyboard(this)
        if (isFinishing) {
            clean()
        }
        pause()
        super.onPause()
    }

    open fun pause() {}

    override fun onStart() {
        super.onStart()
        start()
    }

    open fun start() {}

    override fun onStop() {
        stop()
        super.onStop()
    }

    open fun stop() {}

    override fun onDestroy() {
        destroy()
        clean()
        super.onDestroy()
    }

    private fun clean() {
        for (dialog in dialogs) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
        dialogs.clear()
        for (observer in observers) {
            lifecycle.removeObserver(observer)
        }
        observers.clear()
    }

    open fun destroy() {}

    protected fun addLifecycleObserver(lifecycleObserver: LifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver)
        observers.add(lifecycleObserver)
    }

    protected fun addDialog(dialog: Dialog): Dialog {
        if (!dialogs.add(dialog) && dialog.isShowing) {
            dialog.dismiss()
        }
        return dialog
    }

    protected fun removeDialog(dialog: Dialog) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
        dialogs.remove(dialog)
    }

    fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}