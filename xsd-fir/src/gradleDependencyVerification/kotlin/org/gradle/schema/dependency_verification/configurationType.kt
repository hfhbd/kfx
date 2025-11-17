package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "configurationType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class configurationType(
  @XmlElement
  @XmlSerialName(
    value = "verify-metadata",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `verify-metadata`: Boolean,
  @XmlElement
  @XmlSerialName(
    value = "verify-signatures",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `verify-signatures`: Boolean,
  @XmlElement
  @XmlSerialName(
    value = "keyring-format",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `keyring-format`: String? = null,
  @XmlElement
  @XmlSerialName(
    value = "key-servers",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `key-servers`: `key-serversType`? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-artifacts",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-artifacts`: `trusted-artifactsType`? = null,
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-keys`: `ignored-keysType`? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-keys`: `trusted-keysType`? = null,
)
