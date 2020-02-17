package de.maaxgr.architecturecomponentslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.maaxgr.architecturecompoenentutilities.activityBinding
import de.maaxgr.architecturecompoenentutilities.activityViewModel
import de.maaxgr.architecturecompoenentutilities.enable
import de.maaxgr.architecturecompoenentutilities.observe
import de.maaxgr.architecturecomponentslibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val dataBinding by activityBinding<ActivityMainBinding>(R.layout.activity_main)
    private val viewModel by activityViewModel(MainActivityViewModel::class)

    override fun onCreate(sis: Bundle?) {
        super.onCreate(sis)

        viewModel.enable(this)
        //dataBinding.enablee(this)
        dataBinding.viewModel = viewModel

        viewModel.data.observe(this) {
            println("Observed value: $it")
        }

        this.d()

    }

    fun d() {

    }

}