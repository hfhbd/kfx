package org.gradle.schema.dependency_verification

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trusted-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class TrustedKey(
  @XmlElement
  @XmlSerialName(
    value = "trusting",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trusting: List<Trusting> = emptyList(),
  @SerialName(value = "id")
  public val id: String,
  @SerialName(value = "group")
  public val group: String? = null,
  @SerialName(value = "name")
  public val name: String? = null,
  @SerialName(value = "version")
  public val version: String? = null,
  @SerialName(value = "file")
  public val `file`: String? = null,
  @SerialName(value = "regex")
  public val regex: String? = null,
)
