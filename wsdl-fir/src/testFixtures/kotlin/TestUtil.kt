import io.github.hfhbd.kfx.wsdl.fir.generate
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object TestUtil

@OptIn(ExperimentalPathApi::class)
fun testWsdl(name: String, vararg ignoreFiles: String = arrayOf("WsdlTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generate(
        wsdlFile = TestUtil::class.java.getResourceAsStream("/$name.wsdl"),
        import = {
            TestUtil::class.java.getResourceAsStream("/$it.xsd")
        },
        outputDirectory = outputDirectory,
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$name/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
