package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "artifactType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Artifact(
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `ignored-keys`: IgnoredKeys? = null,
  @XmlElement
  @XmlSerialName(
    value = "pgp",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val pgp: Pgp? = null,
  @XmlElement
  @XmlSerialName(
    value = "md5",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val md5: Md5? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha1",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val sha1: Sha1? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha256",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val sha256: Sha256? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha512",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val sha512: Sha512? = null,
  @SerialName(value = "name")
  public val name: String,
)
