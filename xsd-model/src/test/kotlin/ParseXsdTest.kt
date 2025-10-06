import io.github.hfhbd.kfx.xsd.Attribute
import io.github.hfhbd.kfx.xsd.ComplexType
import io.github.hfhbd.kfx.xsd.Element
import io.github.hfhbd.kfx.xsd.Schema
import io.github.hfhbd.kfx.xsd.xml
import kotlin.test.Test
import kotlin.test.assertEquals

class ParseXsdTest {
    @Test
    fun gradleDependencyVerification() {
        val definition = ParseXsdTest::class.java.getResource("gradleDependencyVerification.xsd").readText()
        val actual = xml().decodeFromString(Schema.serializer(), definition)
        assertEquals(definition, xml().encodeToString(Schema.serializer(), actual))
    }

    @Test
    fun optionalBug() {
        val actual = Schema(
            targetNamespace = "foo",
            complexTypes = listOf(
                ComplexType(
                    "Foo", attributes = listOf(
                        Attribute(name = "bar", type = "string")
                    )
                ),
            )
        )
        // language=xsd
        val expectedXsd = """<?xml version='1.0' encoding='UTF-8' ?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" targetNamespace="foo">
    <complexType name="Foo" mixed="false">
        <attribute name="bar" type="string" use="optional" />
    </complexType>
</schema>"""
        assertEquals(expectedXsd, xml().encodeToString(Schema.serializer(), actual))
    }
}
