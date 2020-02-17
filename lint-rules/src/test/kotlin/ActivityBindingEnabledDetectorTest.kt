import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import de.maaxgr.architecturecomponentutils.lintrules.detectors.ActivityBindingEnabledDetector
import org.junit.Test

class ActivityBindingEnabledDetectorTest : LintDetectorTest() {

    @Test
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
            .expectErrorCount(3)
    }


    override fun getDetector() = ActivityBindingEnabledDetector()

    override fun getIssues() = mutableListOf(ActivityBindingEnabledDetector.ISSUE)
}