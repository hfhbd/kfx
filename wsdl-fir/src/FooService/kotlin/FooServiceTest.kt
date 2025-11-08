import com.example.bar.*
import com.example.foo.*
import com.example.foo.client.createFoo
import com.example.foo.server.createFoo
import io.github.hfhbd.kfx.soap11.*
import io.ktor.serialization.kotlinx.serialization
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import kotlinx.datetime.*
import kotlinx.serialization.json.Json
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
    fun mockTest() = testApplication {
        application {
            routing {
                install(ContentNegotiation) {
                    serialization(
                        io.ktor.http.ContentType.Text.Xml,
                        Json,
                    )
                }
                createFoo { 
                    Envelope(null, Body(it.body.body.bar))
                }
            }
        }
        val response = createClient  {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                serialization(
                    io.ktor.http.ContentType.Text.Xml,
                    Json,
                )
            }
        }.createFoo(
            input = Foo(
                bar = Bar(),
                foo = 42,
            )
        )
        assertEquals(Bar(), response)
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
