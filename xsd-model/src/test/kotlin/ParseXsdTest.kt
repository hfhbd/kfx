import io.github.hfhbd.kfx.xsd.model.Schema
import io.github.hfhbd.kfx.xsd.model.xml
import kotlin.test.Test

class ParseXsdTest {
    @Test
    fun gradleDependencyVerification() {
        val definition = ParseXsdTest::class.java.getResource("gradleDependencyVerification.xsd").readText()
        xml().decodeFromString(Schema.serializer(), definition)
    }

    @Test
    fun parseBar() {
        val text = ParseXsdTest::class.java.getResourceAsStream("/Bar.xsd")!!.bufferedReader().readText()
        xml().decodeFromString(Schema.serializer(), text)
    }
}
