import nl.adaptivity.xmlutil.serialization.XML
import org.gradle.schema.dependency_verification.`verification-metadata`
import kotlin.test.Test

class XsdTesting {
    @Test
    fun gradleDependencyVerification() {
        testXsd("gradleDependencyVerification")
    }

    @Test
    fun decodeGradleDependencyVerification() {
        XML.v1.decodeFromString(
            `verification-metadata`.serializer(),
            XsdTesting::class.java.getResource("/verification-metadata.xml")!!.readText()
        )
    }
}
