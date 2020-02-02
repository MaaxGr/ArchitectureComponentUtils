# Whats ArchitectureComponentUtils?

Just a small library for having a better life as an Android Developer

# Features?

## No lateinit for DataBinding in activities any more

* Requires activity to be a subtype of AppCompatActivity 

```kotlin
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