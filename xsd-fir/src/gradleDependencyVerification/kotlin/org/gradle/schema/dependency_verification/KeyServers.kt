package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "key-serversType",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class KeyServers(
  @XmlElement
  @XmlSerialName(
    value = "key-server",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val `key-server`: List<KeyServer> = emptyList(),
  @SerialName(value = "enabled")
  public val enabled: Boolean? = null,
)
