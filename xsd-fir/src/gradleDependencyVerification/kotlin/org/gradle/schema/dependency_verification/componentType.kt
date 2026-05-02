package org.gradle.schema.dependency_verification

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "componentType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
public data class componentType(
  @XmlElement
  @XmlSerialName(
    value = "artifact",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val artifact: List<artifactType> = emptyList(),
  public val group: String,
  public val name: String,
  public val version: String,
)
