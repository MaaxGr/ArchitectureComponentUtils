package de.maaxgr.architecturecompoenentutilities

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ActivityViewModelProperty<out T : ViewModel>(
    private val kClass: KClass<T>
) : ReadOnlyProperty<AppCompatActivity, T> {

    private var viewModel: T? = null

    override operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        return viewModel ?: initViewModel(thisRef).also { viewModel = it }
    }

    private fun initViewModel(owner: AppCompatActivity): T {
        return ViewModelProvider(owner)[kClass.java]
    }
}
