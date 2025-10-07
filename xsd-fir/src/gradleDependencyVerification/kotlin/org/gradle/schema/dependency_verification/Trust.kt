package org.gradle.schema.dependency_verification

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trustType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class Trust(
  override val group: String? = null,
  override val name: String? = null,
  override val version: String? = null,
  override val regex: Boolean? = null,
  override val file: String? = null,
  val reason: String? = null,
): Coordinates
