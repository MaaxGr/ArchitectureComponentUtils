import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test

class AndroidLogDetectorTest : LintDetectorTest() {

    @Test
    fun testShouldDetectUsageOfAndroidLog() {
        println("TEST")

        val stubFile = kotlin(
            """
            package com.fabiocarballo.lint
            import android.util.Log
            class Dog {
                fun bark() {
                    Log.d(TAG, "woof! woof!")
                }
            }
        """
        ).indented()

        val lintResult = lint()
            .files(stubFile)
            .run()

        lintResult
            .expectErrorCount(1)
    }

    override fun getDetector(): Detector = AndroidLogDetector()

    override fun getIssues(): MutableList<Issue> = mutableListOf(AndroidLogDetector.ISSUE)
}