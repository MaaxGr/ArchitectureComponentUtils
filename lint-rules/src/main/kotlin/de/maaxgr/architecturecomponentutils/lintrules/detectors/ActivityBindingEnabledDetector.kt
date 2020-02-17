package de.maaxgr.architecturecomponentutils.lintrules.detectors

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UClass
import org.jetbrains.uast.getUCallExpression
import java.util.*

class ActivityBindingEnabledDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes() = listOf(UClass::class.java)

    override fun createUastHandler(context: JavaContext) =
        NamingPatternHandler(context)

    class NamingPatternHandler(private val context: JavaContext) : UElementHandler() {
        override fun visitClass(node: UClass) {
            val delegateField = node.fields
                .firstOrNull() {
                    it.name.endsWith("\$delegate")
                            && it.text.contains("by activityBinding<")
                } ?: return

            val variableName = delegateField.name.removeSuffix("\$delegate")
            val onCreate = node.methods.firstOrNull() { it.name == "onCreate" } ?: return
            val enable = onCreate.text.contains("$variableName.enable(")

            if (!enable) {
                println("NOT ENABLED REPORT")
                context.report(
                    issue = ISSUE,
                    scopeClass = node,
                    location = context.getLocation(delegateField),
                    message = "Probably missing $variableName.enable(this)?"
                )
            }
        }
    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            ActivityBindingEnabledDetector::class.java,
            EnumSet.of(Scope.JAVA_FILE)
        )

        val ISSUE: Issue = Issue
            .create(
                id = "ActivityBindingEnabledDetector",
                briefDescription = "My amazon detector",
                explanation = """
                For amazing showcasing.
            """.trimIndent(),
                category = Category.CORRECTNESS,
                priority = 9,
                severity = Severity.ERROR,
                androidSpecific = true,
                implementation = IMPLEMENTATION
            )
    }
}