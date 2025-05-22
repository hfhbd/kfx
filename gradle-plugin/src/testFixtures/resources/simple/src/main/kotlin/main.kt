import client.foo
import client.fooCSRF
import io.github.hfhbd.Foo
import io.github.hfhbd.FooCsv
import io.ktor.client.HttpClient

suspend fun main() {
    val client = HttpClient()
    client.fooCSRF()
    val csv: List<FooCsv> = client.foo(
        input = listOf(Foo(foo = "foo")),
        X_CSRF_Token = "someToken",
    )
    val s = csv.single().FOO
}
