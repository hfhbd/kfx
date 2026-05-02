package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "ignored-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
public data class `ignored-keyType`(
  public val id: String,
  public val reason: String? = null,
)
