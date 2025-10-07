package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trusted-keyType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class TrustedKey(
  @XmlElement
  @XmlSerialName(
    value = "trusting",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trusting: List<Trusting> = emptyList(),
  val id: String,
  val group: String? = null,
  val name: String? = null,
  val version: String? = null,
  val file: String? = null,
  val regex: String? = null,
)
