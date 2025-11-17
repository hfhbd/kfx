package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trustingType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class trustingType(
  public val group: String? = null,
  public val name: String? = null,
  public val version: String? = null,
  public val regex: Boolean? = null,
  public val `file`: String? = null,
)
