package de.maaxgr.architecturecompoenentutilities

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> AppCompatActivity.activityBinding(@LayoutRes resId: Int)
        = ActivityBindingProperty<T>(resId)

fun <T : ViewDataBinding> T.enable(activity: AppCompatActivity) {
    lifecycleOwner = activity
}