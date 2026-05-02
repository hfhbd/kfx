package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "artifactType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
public data class artifactType(
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-keys`: `ignored-keysType`? = null,
  @XmlElement
  @XmlSerialName(
    value = "pgp",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val pgp: pgpType? = null,
  @XmlElement
  @XmlSerialName(
    value = "md5",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val md5: md5Type? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha1",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val sha1: sha1Type? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha256",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val sha256: sha256Type? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha512",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val sha512: sha512Type? = null,
  public val name: String,
)
