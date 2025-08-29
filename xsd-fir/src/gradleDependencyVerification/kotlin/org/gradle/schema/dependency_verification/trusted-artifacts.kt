package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trusted-artifacts",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class `trusted-artifacts`(
  @XmlElement
  @XmlSerialName(
    value = "trust",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trust: List<trust> = emptyList(),
)
