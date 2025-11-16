import io.github.hfhbd.kfx.wsdl.fir.generateWsdl
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.deleteRecursively

private object WsdlTestUtil

@OptIn(ExperimentalPathApi::class)
fun testWsdl(name: String, vararg ignoreFiles: String = arrayOf("WsdlTesting.kt")) {
    val outputDirectory = Path("build/kfx-tests/$name")
    outputDirectory.deleteRecursively()

    generateWsdl(
        wsdlFile = WsdlTestUtil::class.java.getResourceAsStream("/$name.wsdl"),
        import = {
            WsdlTestUtil::class.java.getResourceAsStream("/$it")
        },
        outputDirectory = outputDirectory,
    )
    assertEqualsDirectories(
        expectedDirectory = Path("src/$name/kotlin"),
        actualDirectory = outputDirectory,
        ignoreFiles = ignoreFiles,
    )
}
