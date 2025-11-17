import com.example.bar.Bar
import com.example.bar.BarType
import com.example.foo.Fault
import com.example.foo.Foo
import com.example.foo.client.createFoo
import com.example.foo.client.createFooWithoutFault
import com.example.foo.results.CreateFooResult
import com.example.foo.results.CreateFooWithoutFaultResult
import com.example.foo.server.createFoo
import com.example.foo.server.createFooWithoutFault
import io.github.hfhbd.kfx.soap11.Envelope
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.serialization
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FooServiceTest {
    @Test
    fun test() {
        testWsdl(
            name = "FooService",
            "FooServiceTest.kt",
        )
    }

    @Test
    fun mockSuccessTest() = testApplication {
        application {
            routing {
                install(ContentNegotiation) {
                    serialization(
                        io.ktor.http.ContentType.Text.Xml,
                        Json,
                    )
                }
                createFoo {
                    CreateFooResult.Success(Envelope(null, Bar(it.body.bar.validFrom)))
                }
            }
        }
        val response = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                serialization(
                    io.ktor.http.ContentType.Text.Xml,
                    Json,
                )
            }
        }.createFoo(
            input = Foo(
                bar = BarType(),
                foo = 42,
            )
        )
        assertTrue(response is CreateFooResult.Success)
        assertEquals(Bar(), response.body.body)
    }

    @Test
    fun mockFailureTest() = testApplication {
        application {
            routing {
                install(ContentNegotiation) {
                    serialization(
                        io.ktor.http.ContentType.Text.Xml,
                        Json,
                    )
                }
                createFoo {
                    CreateFooResult.Failure(
                        Envelope(
                            null,
                            Fault(
                                message = "Some client error",
                            )
                        )
                    )
                }
            }
        }
        val response = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                serialization(
                    io.ktor.http.ContentType.Text.Xml,
                    Json,
                )
            }
            Logging {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }.createFoo(
            input = Foo(
                bar = BarType(),
                foo = 42,
            )
        )
        assertTrue(response is CreateFooResult.Failure)
        assertEquals("Some client error", response.body.body.message)
    }

    @Test
    fun mockDefaultFaultTest() = testApplication {
        application {
            routing {
                install(ContentNegotiation) {
                    serialization(
                        io.ktor.http.ContentType.Text.Xml,
                        Json,
                    )
                }
                createFooWithoutFault {
                    CreateFooWithoutFaultResult.Failure(
                        Envelope(
                            null,
                            io.github.hfhbd.kfx.soap11.Fault(
                                faultCode = "",
                                faultString = "Some client error",
                            )
                        )
                    )
                }
            }
        }
        val response = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                serialization(
                    io.ktor.http.ContentType.Text.Xml,
                    Json,
                )
            }
            Logging {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }.createFooWithoutFault(
            input = Foo(
                bar = BarType(),
                foo = 42,
            )
        )
        assertTrue(response is CreateFooWithoutFaultResult.Failure)
        assertEquals("Some client error", response.body.body.faultString)
    }

    @Test
    fun parseTest() {
        val foo = Foo(
            bar = BarType(validFrom = LocalDate.fromEpochDays(42)),
            foo = 42,
            baz = true,
        )
        val fooXml = XML {
            repairNamespaces = false
            xmlVersion = XmlVersion.XML10
            xmlDeclMode = XmlDeclMode.Charset
            autoPolymorphic = true
            indentString = "    "
        }.encodeToString(Foo.serializer(), foo)
        assertEquals(
            FooServiceTest::class.java.getResource("/Foo.xml").readText(),
            fooXml + "\n",
        )
        assertEquals(
            foo,
            XML.decodeFromString(Foo.serializer(), fooXml),
        )
    }
}
