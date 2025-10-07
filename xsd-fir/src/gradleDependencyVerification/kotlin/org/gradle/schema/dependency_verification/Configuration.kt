package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "configurationType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Configuration(
  @XmlElement
  @XmlSerialName(
    value = "verify-metadata",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `verify-metadata`: Boolean? = null,
  @XmlElement
  @XmlSerialName(
    value = "verify-signatures",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `verify-signatures`: Boolean? = null,
  @XmlElement
  @XmlSerialName(
    value = "keyring-format",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `keyring-format`: KeyringFormat? = null,
  @XmlElement
  @XmlSerialName(
    value = "key-servers",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `key-servers`: KeyServers? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-artifacts",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-artifacts`: TrustedArtifacts? = null,
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-keys`: IgnoredKeys? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `trusted-keys`: TrustedKeys? = null,
)
