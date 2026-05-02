package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "artifactType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "artifactType")
public data class artifactType(
  @XmlElement
  @XmlSerialName(
    value = "ignored-keys",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "ignored-keys")
  public val `ignored-keys`: `ignored-keysType`? = null,
  @XmlElement
  @XmlSerialName(
    value = "pgp",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "pgp")
  public val pgp: pgpType? = null,
  @XmlElement
  @XmlSerialName(
    value = "md5",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "md5")
  public val md5: md5Type? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha1",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "sha1")
  public val sha1: sha1Type? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha256",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "sha256")
  public val sha256: sha256Type? = null,
  @XmlElement
  @XmlSerialName(
    value = "sha512",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "sha512")
  public val sha512: sha512Type? = null,
  @SerialName(value = "name")
  public val name: String,
)
