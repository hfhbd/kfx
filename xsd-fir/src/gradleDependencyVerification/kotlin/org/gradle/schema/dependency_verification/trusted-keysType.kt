package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "trusted-keysType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
public data class `trusted-keysType`(
  @XmlElement
  @XmlSerialName(
    value = "trusted-key",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-key`: List<`trusted-keyType`> = emptyList(),
)
