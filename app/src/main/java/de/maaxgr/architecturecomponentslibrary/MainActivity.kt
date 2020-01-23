package de.maaxgr.architecturecomponentslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.maaxgr.architecturecompoenentutilities.activityBinding
import de.maaxgr.architecturecompoenentutilities.enable
import de.maaxgr.architecturecomponentslibrary.databinding.ActivityMainBinding

//Credits to: https://medium.com/@ashdavies/leveraging-android-data-binding-with-kotlin-adfd7b73aeea

class MainActivity : AppCompatActivity() {

    private val dataBinding by activityBinding<ActivityMainBinding>(R.layout.activity_main)

    override fun onCreate(sis: Bundle?) {
        super.onCreate(sis)

        dataBinding.enable(this)
    }
}