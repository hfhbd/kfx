import io.github.hfhbd.kfx.openapi.fir.generateOpenApi
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object OpenApiTestUtil

@OptIn(ExperimentalPathApi::class)
fun testOpenApi(folderName: String, apiName: String = folderName, vararg ignoreFiles: String = arrayOf("OpenApiTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$folderName")
    outputDirectory.deleteRecursively()

    generateOpenApi(
        openApiFile = OpenApiTestUtil::class.java.getResourceAsStream("/$apiName.json"),
        outputDirectory = outputDirectory,
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$folderName/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
