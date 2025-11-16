import io.github.hfhbd.kfx.swagger.fir.generateSwagger
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object SwaggerTestUtil

@OptIn(ExperimentalPathApi::class)
fun testSwagger(name: String, vararg ignoreFiles: String = arrayOf("SwaggerTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generateSwagger(
        swaggerFile = SwaggerTestUtil::class.java.getResourceAsStream("/$name.json"),
        outputDirectory = outputDirectory,
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$name/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
