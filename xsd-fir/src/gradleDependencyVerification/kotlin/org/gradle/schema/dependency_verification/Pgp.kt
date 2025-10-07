package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
    value = "pgpType",
    namespace = "https://schema.gradle.org/dependency-verification",
)
data class Pgp(
    val value: String,
)
