package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "coordinatesType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public sealed interface Coordinates {
  val group: String?
  val name: String?
  val version: String?
  val regex: String?
  val file: String?
}
