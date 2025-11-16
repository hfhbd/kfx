import kotlin.test.Test

class OpenApiTesting {
    @Test
    fun central() {
        testOpenApi("central", "OpenApiTesting.kt", "CentralPackageName.kt")
    }
}
