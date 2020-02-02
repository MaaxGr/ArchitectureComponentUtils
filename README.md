# Whats ArchitectureComponentUtils?

Just a small library for having a better life as an Android Developer

# Features?

## No lateinit for DataBinding in activities any more

* Requires activity to be a subtype of AppCompatActivity 

```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.maaxgr.architecturecompoenentutilities.activityBinding
import de.maaxgr.architecturecompoenentutilities.enable
import de.maaxgr.architecturecomponentslibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //define dataBinding variable with delegate
    private val dataBinding by activityBinding<ActivityMainBinding>(R.layout.activity_main)

    override fun onCreate(sis: Bundle?) {
        super.onCreate(sis)

        //init data binding and set lifecycleOwner to this
        dataBinding.enable(this)
    }
}
``` 