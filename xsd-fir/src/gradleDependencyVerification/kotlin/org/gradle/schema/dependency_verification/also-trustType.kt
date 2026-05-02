package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "also-trustType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
public data class `also-trustType`(
  public val `value`: String? = null,
)
