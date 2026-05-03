package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "ignored-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "ignored-keyType")
public data class `ignored-keyType`(
  @SerialName(value = "id")
  public val id: String,
  @SerialName(value = "reason")
  public val reason: String? = null,
)
