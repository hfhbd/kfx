package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "coordinatesType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public sealed interface Coordinates {
  @SerialName(value = "group")
  public val group: String?

  @SerialName(value = "name")
  public val name: String?

  @SerialName(value = "version")
  public val version: String?

  @SerialName(value = "regex")
  public val regex: Boolean?

  @SerialName(value = "file")
  public val file: String?
}
