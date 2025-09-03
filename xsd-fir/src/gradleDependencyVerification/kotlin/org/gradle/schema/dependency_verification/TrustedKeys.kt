package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
  value = "trusted-keys",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public data class TrustedKeys(
  @XmlElement
  @XmlSerialName(
    value = "trusted-key",
    namespace = "https://schema.gradle.org/dependency-verification",
  )
  public val trustedKey: List<TrustedKey> = emptyList(),
)
