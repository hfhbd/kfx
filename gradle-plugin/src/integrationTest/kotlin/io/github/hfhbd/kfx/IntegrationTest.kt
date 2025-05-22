package io.github.hfhbd.kfx

import org.gradle.testkit.runner.*
import java.nio.file.*
import kotlin.io.path.*
import kotlin.test.*

@ExperimentalPathApi
class IntegrationTest {
    private val fixtureDir = Path(System.getenv("fixtureDir"))

    @Test
    fun simpleWorks() {
        val projectDir = fixtureDir / "resources" / "simple"
        build(projectDir, ":assemble")
    }

    private fun build(projectDir: Path, vararg tasks: String): BuildResult {
        return GradleRunner.create()
            .withPluginClasspath()
            .withProjectDir(projectDir.toFile())
            .forwardOutput()
            .withArguments(
                "clean",
                *tasks,
                "--configuration-cache",
            )
            .build()
    }
}
