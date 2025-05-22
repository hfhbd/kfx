package io.github.hfhbd.kfx.ir

import kotlin.test.*

class ODataTest {
    @Test
    fun testTransform() {
        assertEquals(
            "GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion",
            "GetIntegrationPackages('{Id}')MessageMappingDesigntimeArtifacts(Id='{ArtifactId}',Version='{ArtifactVersion}')".transform(),
        )
    }
}
