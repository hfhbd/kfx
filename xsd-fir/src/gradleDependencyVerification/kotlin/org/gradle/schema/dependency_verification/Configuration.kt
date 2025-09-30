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
public data class Configuration(
  @XmlElement
  @XmlSerialName(
    value = "verify-metadata",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val verifyMetadata: Boolean,
  @XmlElement
  @XmlSerialName(
    value = "verify-signatures",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val verifySignatures: Boolean,
  @XmlElement
  @XmlSerialName(
    value = "keyring-format",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val keyringFormat: String? = null,
  @XmlElement
  @XmlSerialName(
    value = "key-servers",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val keyServers: KeyServers? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-artifacts",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trustedArtifacts: TrustedArtifacts? = null,
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val ignoredKeys: IgnoredKeys? = null,
  @XmlElement
  @XmlSerialName(
    value = "trusted-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trustedKeys: TrustedKeys? = null,
)
