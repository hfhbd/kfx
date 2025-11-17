package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "verification-metadataType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class `verification-metadataType`(
  @XmlElement
  @XmlSerialName(
    value = "configuration",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val configuration: configurationType,
  @XmlElement
  @XmlSerialName(
    value = "components",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val components: componentsType,
)
