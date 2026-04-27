import com.example.FooInputEnum
import kotlinx.serialization.json.Json
import com.example.TestEnumInt
import kotlinx.serialization.builtins.ListSerializer
import kotlin.test.Test
import kotlin.test.assertEquals

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
}
