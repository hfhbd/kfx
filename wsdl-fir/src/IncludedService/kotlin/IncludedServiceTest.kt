import kotlin.test.Test

class IncludedServiceTest {
    @Test
    fun test() {
        testWsdl(
            name = "IncludedService",
            "IncludedServiceTest.kt",
        )
    }
}
