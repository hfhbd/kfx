import client.deleteHttpFooBarBaz
import client.queryHttpFooBarBaz
import com.example.Fault
import com.example.FooInput
import com.example.FooInputEnum
import com.example.TestEnumInt
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpStatusCode.Companion.BadGateway
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import results.DeleteHttpFooBarBazResult
import results.QueryHttpFooBarBazResult
import server.deleteHttpFooBarBaz
import server.queryHttpFooBarBaz
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OpenApiTesting {
    @Test
    fun a() {
        testOpenApi("a")
    }

    @Test
    fun stringEnumTest() {
        assertEquals(FooInputEnum.C1, Json.decodeFromString(FooInputEnum.serializer(), "\"C.1\""))
        assertEquals("\"C.1\"", Json.encodeToString(FooInputEnum.serializer(), FooInputEnum.C1))
    }

    @Test
    fun intEnumTest() {
        val json = Json
        assertEquals("1", json.encodeToString(TestEnumInt.serializer(), TestEnumInt.`1`))
        assertEquals(TestEnumInt.`2`, json.decodeFromString(TestEnumInt.serializer(), "2"))
        assertEquals(
            listOf(TestEnumInt.`1`, TestEnumInt.`2`),
            json.decodeFromString(ListSerializer(TestEnumInt.serializer()), "[1, 2]")
        )
    }

    @Test
    fun integrationTest() = testApplication {
        application {
            routing {
                install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                    json()
                }
                queryHttpFooBarBaz {
                    response.status(BadGateway)
                    QueryHttpFooBarBazResult.Failure(
                        body = Fault(
                            httpReturnCode = BadGateway.value,
                            input = "",
                            message = ""
                        )
                    )
                }
                deleteHttpFooBarBaz {
                    response.status(Created)
                    DeleteHttpFooBarBazResult.Success(
                        body = "Foo",
                    )
                }
            }
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
            Logging {
                level = LogLevel.ALL
                logger = Logger.SIMPLE
            }
        }

        val queryResult = client.config {
            this.install(createClientPlugin("validateStatusCode") {
                this.onResponse { response ->
                    assertEquals(response.status, BadGateway)
                }
            })
        }.queryHttpFooBarBaz(FooInput("FF"), "Token")
        assertTrue(queryResult is QueryHttpFooBarBazResult.Failure)
        assertEquals(
            Fault(
                httpReturnCode = BadGateway.value,
                input = "",
                message = ""
            ), queryResult.body
        )

        val deleteResult = client.config {
            this.install(createClientPlugin("validateStatusCode") {
                this.onResponse { response ->
                    assertEquals(response.status, Created)
                }
            })
        }.deleteHttpFooBarBaz(FooInput("FF"), "Token")
        assertTrue(deleteResult is DeleteHttpFooBarBazResult.Success)
        assertEquals("Foo", deleteResult.body
        )
    }
}
