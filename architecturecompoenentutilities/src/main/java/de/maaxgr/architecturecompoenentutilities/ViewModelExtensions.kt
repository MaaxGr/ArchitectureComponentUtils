package de.maaxgr.architecturecompoenentutilities

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlin.reflect.KClass

fun <T : ViewModel> AppCompatActivity.activityViewModel(
    kClass: KClass<T>
) = ActivityViewModelProperty<T>(kClass)

fun <T : ViewModel> T.enable(activity: AppCompatActivity) {
    //access view model property to start initialization
    val context = this.viewModelScope.coroutineContext
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, onChanged: (T) -> Unit) {
    this.observe(owner, Observer { onChanged.invoke(it) })
}