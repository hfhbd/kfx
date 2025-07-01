import org.opentest4j.AssertionFailedError
import org.opentest4j.FileInfo
import kotlin.test.assertEquals
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.pathString
import kotlin.io.path.readText
import kotlin.io.path.relativeTo
import kotlin.io.path.walk

fun assertEqualsDirectories(
    expectedDirectory: Path,
    actualDirectory: Path,
    vararg ignoreFiles: String,
) {
    require(expectedDirectory.exists())
    require(expectedDirectory.isDirectory())

    val expectedFiles =
        expectedDirectory.walk().associate { it.relativeTo(expectedDirectory).pathString to it.readText() }
    val actualFiles = actualDirectory.walk().associate { it.relativeTo(actualDirectory).pathString to it.readText() }

    val expectedFileNames = expectedFiles.keys - ignoreFiles
    val actualFileNames = actualFiles.keys
    val missingFiles = expectedFileNames - actualFileNames
    val unexpectedFiles = actualFileNames - expectedFileNames

    val message = if (missingFiles.isNotEmpty()) {
        "Missing files: $missingFiles"
    } else if (unexpectedFiles.isNotEmpty()) {
        "Unexpected files: $unexpectedFiles"
    } else null
    assertEquals(expectedFileNames, actualFileNames, message)

    for ((actualPath, actualContent) in actualFiles) {
        val expectedContent = expectedFiles[actualPath]!!
        if (actualContent != expectedContent) {
            throw AssertionFailedError(
                "File content does not match",
                FileInfo(
                    actualPath,
                    expectedContent.toByteArray(),
                ),
                actualContent,
            )
        }
        assertEquals(expectedFiles[actualPath], actualContent)
    }
}
