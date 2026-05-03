package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "key-serverType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "key-serverType")
public data class `key-serverType`(
  @SerialName(value = "uri")
  public val uri: String? = null,
)
