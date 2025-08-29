package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "configuration",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class configuration(
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
  public val `key-servers`: `key-servers`? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-artifacts",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-artifacts`: `trusted-artifacts`? = null,
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-keys`: `ignored-keys`? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-keys`: `trusted-keys`? = null,
)
