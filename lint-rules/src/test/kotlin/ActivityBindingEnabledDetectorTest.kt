/*
class ActivityBindingEnabledDetectorTest : LintDetectorTest() {

    //@Test
    fun testShouldDetectUsageOfAndroidLog() {
        println("TEST")

        val stubFile = kotlin(
            """
            import android.os.Bundle
            import androidx.appcompat.app.AppCompatActivity
            import de.maaxgr.architecturecompoenentutilities.activityBinding
            import de.maaxgr.architecturecompoenentutilities.activityViewModel
            import de.maaxgr.architecturecompoenentutilities.enable
            import de.maaxgr.architecturecompoenentutilities.observe
            import de.maaxgr.architecturecomponentslibrary.databinding.ActivityMainBinding
            import android.util.Log
                
            class MainActivity : AppCompatActivity() {

                private val dataBinding by activityBinding<ActivityMainBinding>(R.layout.activity_main)
                private val viewModel by activityViewModel(MainActivityViewModel::class)

                override fun onCreate(sis: Bundle?) {
                    super.onCreate(sis)

                    viewModel.enable(this)
                    dataBinding.enablee(this)
                    dataBinding.viewModel = viewModel

                    viewModel.data.observe(this) {
                        println("Observed value:")
                    }
                    Log.e(TAG, "woof! woof!")
                    Log.d(TAG, "woof! woof!")
                    this.enable()
                }
    
                fun enable() {
                }
            }
        """
        ).indented()

        val lintResult = lint()
            .files(stubFile)
            .run()

        lintResult
            .expect("No warnings.")
    }


    override fun getDetector() = ActivityBindingEnabledDetector()

    override fun getIssues() = mutableListOf(ActivityBindingEnabledDetector.ISSUE)
}

 */