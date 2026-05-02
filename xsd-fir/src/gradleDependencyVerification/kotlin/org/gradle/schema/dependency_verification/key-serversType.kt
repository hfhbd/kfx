package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@XmlSerialName(
  value = "key-serversType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
@Serializable
@SerialName(value = "key-serversType")
public data class `key-serversType`(
  @XmlElement
  @XmlSerialName(
    value = "key-server",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  @SerialName(value = "key-server")
  public val `key-server`: List<`key-serverType`> = emptyList(),
  @SerialName(value = "enabled")
  public val enabled: Boolean? = null,
)
