package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trustingType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Trusting(
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
