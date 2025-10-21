import io.github.hfhbd.kfx.swagger.fir.generate
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object TestUtil

@OptIn(ExperimentalPathApi::class)
fun test(name: String, vararg ignoreFiles: String = arrayOf("SwaggerTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generate(
        swaggerFile = TestUtil::class.java.getResourceAsStream("/$name.json"),
        outputDirectory = outputDirectory,
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$name/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
