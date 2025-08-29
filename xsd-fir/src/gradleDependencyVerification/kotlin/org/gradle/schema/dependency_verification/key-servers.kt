package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "key-servers",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class `key-servers`(
  @XmlElement
  @XmlSerialName(
    value = "key-server",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `key-server`: List<`key-server`> = emptyList(),
)
