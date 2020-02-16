package de.maaxgr.architecturecompoenentutilities

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

//Credits to: https://medium.com/@ashdavies/leveraging-android-data-binding-with-kotlin-adfd7b73aeea

class ActivityBindingProperty<out T : ViewDataBinding>(
    @LayoutRes private val resId: Int
) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createBinding(thisRef).also { binding = it }
    }

    private fun createBinding(activity: Activity): T {
        return DataBindingUtil.setContentView(activity, resId)
    }
}
