package org.gradle.schema.dependency_verification

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "sha512Type",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "sha512Type")
public data class sha512Type(
  @XmlElement
  @XmlSerialName(
    value = "also-trust",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "also-trust")
  public val `also-trust`: List<`also-trustType`> = emptyList(),
  @SerialName(value = "value")
  public val `value`: String,
  @SerialName(value = "origin")
  public val origin: String? = null,
  @SerialName(value = "reason")
  public val reason: String? = null,
)
