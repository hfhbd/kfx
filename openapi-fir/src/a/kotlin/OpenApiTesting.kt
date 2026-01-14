import com.example.FooInputEnum
import kotlinx.serialization.json.Json
import com.example.TestEnumInt
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

    @Test
    fun intEnumTest() {
        val json = Json
        assertEquals("42", json.encodeToString(TestEnumInt.serializer(), TestEnumInt.`1`))
        assertEquals(TestEnumInt.`2`, json.decodeFromString(TestEnumInt.serializer(), "2"))
    }
}
