import nl.adaptivity.xmlutil.serialization.XML
import org.gradle.schema.dependency_verification.VerificationMetadata
import kotlin.test.Test

class XsdTesting {
    @Test
    fun gradleDependencyVerification() {
        test("gradleDependencyVerification")
    }

    @Test
    fun decode() {
        XML.decodeFromString(VerificationMetadata.serializer(), XsdTesting::class.java.getResource("/verification-metadata.xml")!!.readText())
    }
}
