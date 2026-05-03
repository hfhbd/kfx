package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "componentsType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "componentsType")
public data class componentsType(
  @XmlElement
  @XmlSerialName(
    value = "component",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "component")
  public val component: List<componentType> = emptyList(),
)
