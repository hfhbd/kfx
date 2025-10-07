package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "ignored-keysType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class IgnoredKeys(
  @XmlElement
  @XmlSerialName(
    value = "ignored-key",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-key`: List<IgnoredKey> = emptyList(),
)
