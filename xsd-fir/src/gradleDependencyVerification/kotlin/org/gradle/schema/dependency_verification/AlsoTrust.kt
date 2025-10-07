package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
    value = "also-trustType",
    namespace = "https://schema.gradle.org/dependency-verification",
)
data class AlsoTrust(val value: String? = null) {
}
