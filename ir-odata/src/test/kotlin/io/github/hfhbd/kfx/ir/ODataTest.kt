package io.github.hfhbd.kfx.ir

import io.github.hfhbd.kfx.ir.odata.transform
import kotlin.test.Test
import kotlin.test.assertEquals

class ODataTest {
    @Test
    fun testTransform() {
        assertEquals(
            "GetIntegrationPackagesByIdMessageMappingDesigntimeArtifactsByArtifactIdAndArtifactVersion",
            "GetIntegrationPackages('{Id}')MessageMappingDesigntimeArtifacts(Id='{ArtifactId}',Version='{ArtifactVersion}')".transform(),
        )
    }
}
