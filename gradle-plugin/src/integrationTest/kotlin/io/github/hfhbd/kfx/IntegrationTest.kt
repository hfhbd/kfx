package io.github.hfhbd.kfx

import org.gradle.testkit.runner.*
import java.io.File
import kotlin.test.*

class IntegrationTest {

    @Test
    fun simpleWorks() {
        val projectDir = File("src/testFixtures/resources/simple")
        build(projectDir, ":assemble")
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
