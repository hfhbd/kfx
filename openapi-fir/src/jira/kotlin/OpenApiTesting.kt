import kotlin.test.Test

class OpenApiTesting {
    @Test
    fun jira() {
        testOpenApi("jira", "OpenApiTesting.kt", "JiraPackageName.kt")
    }
}
