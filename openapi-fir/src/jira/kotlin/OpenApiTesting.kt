import kotlin.test.Test

class OpenApiTesting {
    @Test
    fun jira() {
        testOpenApi("jira", ignoreFiles = arrayOf("OpenApiTesting.kt", "JiraPackageName.kt"))
    }
}
