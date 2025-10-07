package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
    value = "md5Type",
    namespace = "https://schema.gradle.org/dependency-verification",
)
data class Md5(
    val alsoTrust: List<AlsoTrust> = emptyList(),
    val value: String,
    val origin: String? = null,
    val reason: String? = null,
) {
}
