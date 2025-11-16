import io.github.hfhbd.kfx.openapi.fir.generateOpenApi
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object OpenApiTestUtil

@OptIn(ExperimentalPathApi::class)
fun testOpenApi(name: String, vararg ignoreFiles: String = arrayOf("OpenApiTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generateOpenApi(
        openApiFile = OpenApiTestUtil::class.java.getResourceAsStream("/$name.json"),
        outputDirectory = outputDirectory,
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$name/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
