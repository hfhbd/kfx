package org.gradle.schema.dependency_verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "verification-metadataType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "verification-metadataType")
public data class `verification-metadataType`(
  @XmlElement
  @XmlSerialName(
    value = "configuration",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "configuration")
  public val configuration: configurationType,
  @XmlElement
  @XmlSerialName(
    value = "components",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "components")
  public val components: componentsType,
)
