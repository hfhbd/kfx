import kotlin.test.Test

class OpenApiTesting {
    @Test
    fun central() {
        testOpenApi("central", ignoreFiles = arrayOf("OpenApiTesting.kt", "CentralPackageName.kt"))
    }
}
