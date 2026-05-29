import com.example.FooInputEnum
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenApiTesting {
    @Test
    fun a() {
        testOpenApi("a")
    }

    @Test
    fun decodeEnum() {
        assertEquals(FooInputEnum.C1, Json.decodeFromString(FooInputEnum.serializer(), "\"C.1\""))
    }
}
