package io.github.hfhbd.kfx

import org.gradle.testkit.runner.*
import java.io.File
import kotlin.io.path.*
import kotlin.test.*

@ExperimentalPathApi
class IntegrationTest {
    private val fixtureDir = Path(System.getenv("fixtureDir"))

    @Test
    fun simpleWorks() {
        val projectDir = fixtureDir / "resources" / "simple"
        build(projectDir.toFile(), ":assemble")
    }

    @Test
    fun softwareTypes() {
        val projectDir = fixtureDir / "resources" / "software-types"
        build(projectDir.toFile(), ":assemble")
    }

    private fun build(projectDir: File, vararg tasks: String): BuildResult {
        return GradleRunner.create()
            .withProjectDir(projectDir)
            .forwardOutput()
            .withArguments(
                "clean",
                *tasks,
                "--configuration-cache",
            )
            .build()
    }
}
