package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
    value = "sha256",
    namespace = "https://schema.gradle.org/dependency-verification",
)
data class Sha256(
    val also: List<Also> = emptyList(),
    override val name: String,
    val origin: String? = null,
    val reason: String? = null
) : Artifact {
}
