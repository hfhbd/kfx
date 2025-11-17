package org.gradle.schema.dependency_verification

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@JvmInline
@Serializable
@XmlSerialName(
  value = "verification-metadata",
  namespace = "https://schema.gradle.org/dependency-verification",
)
public value class `verification-metadata` private constructor(
  private val _value: `verification-metadataType`,
) {
  public val configuration: configurationType
    get() = _value.configuration

  public val components: componentsType
    get() = _value.components

  public constructor(configuration: configurationType, components: componentsType) : this(`verification-metadataType`(configuration, components))
}
