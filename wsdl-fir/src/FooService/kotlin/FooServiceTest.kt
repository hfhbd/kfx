import com.example.bar.*
import com.example.foo.*
import kotlinx.datetime.*
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.*
import kotlin.test.Test
import kotlin.test.assertEquals

class FooServiceTest {
    @Test
    fun test() {
        testWsdl(
            name = "FooService",
            "FooServiceTest.kt",
        )
    }

    @Test
    fun parseTest() {
        val foo = Foo(
            bar = Bar(validFrom = LocalDate.fromEpochDays(42)),
            foo = 42,
        )
        val fooXml = XML {
            repairNamespaces = false
            xmlVersion = XmlVersion.XML10
            xmlDeclMode = XmlDeclMode.Charset
            autoPolymorphic = true
            indentString = "    "
        }.encodeToString(Foo.serializer(), foo)
        assertEquals(
            fooXml + "\n",
            FooServiceTest::class.java.getResource("/Foo.xml").readText(),
        )
        assertEquals(
            foo,
            XML.decodeFromString(Foo.serializer(), fooXml),
        )
    }
}
