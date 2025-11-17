package org.gradle.schema.dependency_verification

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "sha1Type",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class sha1Type(
  @XmlElement
  @XmlSerialName(
    value = "also-trust",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `also-trust`: List<`also-trustType`> = emptyList(),
  public val `value`: String,
  public val origin: String? = null,
  public val reason: String? = null,
)
