package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "md5Type",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Md5(
  @XmlElement
  @XmlSerialName(
    value = "also-trust",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `also-trust`: List<AlsoTrust> = emptyList(),
  public val value: String,
  public val origin: String? = null,
  public val reason: String? = null,
)
