package io.github.hfhbd.kfx.ir

import io.github.hfhbd.kfx.ir.odata.transform
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
