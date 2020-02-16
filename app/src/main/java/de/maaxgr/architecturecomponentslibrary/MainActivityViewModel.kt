package de.maaxgr.architecturecomponentslibrary

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val data = MutableLiveData<String>().apply { postValue("Hello View Model!") }

    init {
        Log.d("ARCH_COMP_LIB", "Init VM")
    }

    override fun onCleared() {
        super.onCleared()

        Log.d("ARCH_COMP_LIB", "Cleared VM")
    }


}