import io.github.hfhbd.kfx.xsd.generateXsd
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object XsdTestUtil

@OptIn(ExperimentalPathApi::class)
fun testXsd(name: String, vararg ignoreFiles: String = arrayOf("XsdTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generateXsd(
        xsdFile = XsdTestUtil::class.java.getResourceAsStream("/$name.xsd"),
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
