package org.gradle.schema.dependency_verification

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "componentType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Component(
  @XmlElement
  @XmlSerialName(
    value = "artifact",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val artifact: List<Artifact> = emptyList(),
  @SerialName(value = "group")
  public val group: String,
  @SerialName(value = "name")
  public val name: String,
  @SerialName(value = "version")
  public val version: String,
)
