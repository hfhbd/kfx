package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "ignored-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class IgnoredKey(
  val id: String,
  val reason: String? = null,
)
