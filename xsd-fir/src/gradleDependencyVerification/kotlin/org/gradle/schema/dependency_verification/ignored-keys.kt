package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "ignored-keys",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class `ignored-keys`(
  @XmlElement
  @XmlSerialName(
    value = "ignored-key",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-key`: List<`ignored-key`> = emptyList(),
)
