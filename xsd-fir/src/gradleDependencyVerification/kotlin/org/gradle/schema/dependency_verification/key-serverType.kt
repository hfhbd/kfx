package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "key-serverType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class `key-serverType`(
  public val uri: String? = null,
)
