package de.maaxgr.architecturecomponentutils.lintrules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import de.maaxgr.architecturecomponentutils.lintrules.detectors.ActivityBindingEnabledDetector
import de.maaxgr.architecturecomponentutils.lintrules.detectors.ActivityViewModelEnabledDetector

class IssueRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            ActivityBindingEnabledDetector.ISSUE,
            ActivityViewModelEnabledDetector.ISSUE
        )
}