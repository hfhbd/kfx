package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "verification-metadata",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class VerificationMetadata(
  @XmlElement
  @XmlSerialName(
    value = "configuration",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val configuration: Configuration,
  @XmlElement
  @XmlSerialName(
    value = "components",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val components: Components,
)
