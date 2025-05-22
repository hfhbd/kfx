import client.foo
import io.ktor.client.HttpClient

suspend fun main() {
    HttpClient().foo()
}
