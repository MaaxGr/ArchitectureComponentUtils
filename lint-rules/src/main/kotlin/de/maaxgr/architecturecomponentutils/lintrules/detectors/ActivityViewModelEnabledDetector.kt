package de.maaxgr.architecturecomponentutils.lintrules.detectors

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UClass
import org.jetbrains.uast.UElement
import java.util.*

class ActivityViewModelEnabledDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes() = listOf(UClass::class.java)

    override fun createUastHandler(context: JavaContext) =
        NamingPatternHandler(context)

    class NamingPatternHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitClass(node: UClass) {
            val delegateField = node.fields
                .firstOrNull {
                    it.name.endsWith("\$delegate")
                            && it.type.toString() == "PsiType:ActivityViewModelProperty"
                } ?: return

            val variableName = delegateField.name.removeSuffix("\$delegate")
            val onCreate = node.methods.firstOrNull { it.name == "onCreate" } ?: return
            val enable = onCreate.text.contains("$variableName.enable(")
                    && !onCreate.text.contains("//$variableName.enable(")

            if (!enable) {
                context.report(
                    issue = ISSUE,
                    scope = delegateField as UElement,
                    location = context.getNameLocation(delegateField),
                    message = "Probably missing $variableName.enable(this)?"
                )
            }
        }
    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            ActivityViewModelEnabledDetector::class.java,
            EnumSet.of(Scope.JAVA_FILE)
        )

        val ISSUE: Issue = Issue
            .create(
                id = "ActivityViewModelEnabledDetector",
                briefDescription = "activity-viewmodel enable() detector",
                explanation = """
                checks whether viewmodel.enable() is called in onCreate().
            """.trimIndent(),
                category = Category.CORRECTNESS,
                priority = 9,
                severity = Severity.ERROR,
                androidSpecific = true,
                implementation = IMPLEMENTATION
            )
    }
}