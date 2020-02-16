# Whats ArchitectureComponentUtils?

Just a small library for having a better life as an Android Developer

# How to use the library?

1. Add jitpack to repositories
```kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. add library to dependencies
```kotlin
dependencies {
    ...
    implementation "com.github.MaaxGr:ArchitectureComponentUtils:1.0.0"
}
```


# Features?

## No lateinit for DataBinding and ViewModel in activities any more

* Requires activity to be a subtype of AppCompatActivity 

```kotlin
class MainActivity : AppCompatActivity() {

    //define dataBinding variable with delegate
    private val dataBinding by activityBinding<ActivityMainBinding>(R.layout.activity_main)
    private val viewModel by activityViewModel(MainActivityViewModel::class)


    override fun onCreate(sis: Bundle?) {
        super.onCreate(sis)

        //init data binding and set lifecycleOwner to this
        dataBinding.enable(this)
        viewModel.enable(this)
    }
}
```