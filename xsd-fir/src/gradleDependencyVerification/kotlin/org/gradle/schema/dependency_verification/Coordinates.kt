package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "coordinatesType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Coordinates(
  val group: String? = null,
  val name: String? = null,
  val version: String? = null,
  val regex: String? = null,
  val file: String? = null,
)
