package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "key-serverType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class KeyServer(
  @SerialName(value = "uri")
  public val uri: String? = null,
)
