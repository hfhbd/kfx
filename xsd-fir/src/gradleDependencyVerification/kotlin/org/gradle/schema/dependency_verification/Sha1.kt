package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
    value = "sha1Type",
    namespace = "https://schema.gradle.org/dependency-verification",
)
data class Sha1(
    val alsoTrust: List<AlsoTrust> = emptyList(),
    val value: String,
    val origin: String? = null,
    val reason: String? = null,
) {
}
