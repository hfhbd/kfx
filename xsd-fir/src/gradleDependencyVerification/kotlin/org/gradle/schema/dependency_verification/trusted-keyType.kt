package org.gradle.schema.dependency_verification

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "trusted-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
public data class `trusted-keyType`(
  @XmlElement
  @XmlSerialName(
    value = "trusting",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trusting: List<trustingType> = emptyList(),
  public val id: String,
  public val group: String? = null,
  public val name: String? = null,
  public val version: String? = null,
  public val `file`: String? = null,
  public val regex: String? = null,
)
