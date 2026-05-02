package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "trustingType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "trustingType")
public data class trustingType(
  @SerialName(value = "group")
  public val group: String? = null,
  @SerialName(value = "name")
  public val name: String? = null,
  @SerialName(value = "version")
  public val version: String? = null,
  @SerialName(value = "regex")
  public val regex: Boolean? = null,
  @SerialName(value = "file")
  public val `file`: String? = null,
)
