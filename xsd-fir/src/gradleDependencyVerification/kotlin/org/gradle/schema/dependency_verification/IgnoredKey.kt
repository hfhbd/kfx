package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "ignored-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class IgnoredKey(
  @SerialName(value = "id")
  public val id: String,
  @SerialName(value = "reason")
  public val reason: String? = null,
)
