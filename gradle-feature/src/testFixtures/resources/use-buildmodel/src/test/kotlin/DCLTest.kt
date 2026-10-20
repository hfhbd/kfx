import client.foo
import client.fooCSRF
import server.fooCSRF as serverFooCSRF
import server.foo as serverFoo
import io.github.hfhbd.Foo
import io.github.hfhbd.FooCsv
import io.ktor.client.HttpClient
import io.ktor.server.testing.testApplication
import io.ktor.server.routing.routing
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.serialization

class DCLTest {
    @kotlin.test.Test
    fun fooTest() = testApplication {
        application {
            routing {
                install(ContentNegotiation) {
                    serialization(
                        io.ktor.http.ContentType.Text.CSV,
                        Json,
                    )
                    serialization(
                        io.ktor.http.ContentType.Application.Json,
                        Json,
                    )
                }
                serverFooCSRF {

                }
                serverFoo {
                    emptyList()
                }
            }
        }
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                serialization(
                    io.ktor.http.ContentType.Application.Json,
                    Json,
                )
                serialization(
                    io.ktor.http.ContentType.Text.CSV,
                    Json,
                )
            }
        }

        client.fooCSRF()
        client.foo(
            input = listOf(Foo(foo = "foo")),
            X_CSRF_Token = "someToken",
        )
    }
}
