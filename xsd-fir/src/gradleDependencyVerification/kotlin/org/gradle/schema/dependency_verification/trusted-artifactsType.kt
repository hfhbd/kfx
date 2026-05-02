package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "trusted-artifactsType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "trusted-artifactsType")
public data class `trusted-artifactsType`(
  @XmlElement
  @XmlSerialName(
    value = "trust",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "trust")
  public val trust: List<trustType> = emptyList(),
)
