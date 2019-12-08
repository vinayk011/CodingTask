package com.android.codingtask.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.google.android.material.snackbar.Snackbar
import java.util.*


open class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var binding: T
    protected val root: View by lazy {
        binding.root
    }
    private val dialogs = HashSet<Dialog>()
    private val observers = HashSet<LifecycleObserver>()

    protected fun observeClick(view: View) {
        view.setOnTouchListener { _, _ -> true }
    }

    open override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    open override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    open fun init() {}

    override fun onResume() {
        super.onResume()
        resume()
    }

    open fun resume() {}

    override fun onPause() {
        if (isRemoving) {
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

    open fun destroy() {}
    open fun destroyView() {}

    override fun onDestroyView() {
        destroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        destroy()
        clean()
        super.onDestroy()
        System.gc()
        Runtime.getRuntime().gc()
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

    fun showSnackBar(message: String) {
        snackBar(message).show()
    }

    fun showSnackBar(message: String, duration: Int) {
        snackBar(message, duration).show()
    }
    private fun snackBar(message: String, duration: Int): Snackbar =
        Snackbar.make((context as Activity).findViewById(android.R.id.content), message, duration)

    fun snackBar(message: String): Snackbar =
        Snackbar.make(
            (context as Activity).findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG
        )


    fun toast(message: String) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}