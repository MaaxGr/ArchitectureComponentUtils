package de.maaxgr.architecturecomponentutils.lintrules.detectors

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import org.jetbrains.uast.UClass
import java.util.*

class NoCamelDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE_NAMING_PATTERN
            get() = Issue.create("NamingPattern",
                "Names should be well named.",
                "Some long description about this issue",
                CORRECTNESS,
                5,
                Severity.WARNING,
                Implementation(NoCamelDetector::class.java,
                    EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES))
            )
    }

    override fun getApplicableUastTypes() = listOf(UClass::class.java)


    override fun createUastHandler(context: JavaContext) =
        NamingPatternHandler(context)class NamingPatternHandler(private val context: JavaContext) :
        UElementHandler() {
        override fun visitClass(clazz: UClass) {
            if (clazz.name?.isDefinedCamelCase() == false) {
                context.report(ISSUE_NAMING_PATTERN, clazz,
                    context.getNameLocation(clazz),
                    "Not named in defined camel case.")
            }
        }

        private fun String.isDefinedCamelCase(): Boolean {
            val charArray = toCharArray()
            return charArray
                .mapIndexed { index, current ->
                    current to charArray.getOrNull(index + 1)
                }
                .none {
                    it.first.isUpperCase() && it.second?.isUpperCase() ?: false
                }
        }
    }



}