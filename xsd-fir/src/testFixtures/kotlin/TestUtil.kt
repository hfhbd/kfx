import io.github.hfhbd.kfx.xsd.generate
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object TestUtil

@OptIn(ExperimentalPathApi::class)
fun test(name: String, vararg ignoreFiles: String = arrayOf("XsdTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generate(
        xsdFile = TestUtil::class.java.getResourceAsStream("/$name.xsd"),
        outputDirectory = outputDirectory,
        import = {
            error(it)
        }
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$name/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
