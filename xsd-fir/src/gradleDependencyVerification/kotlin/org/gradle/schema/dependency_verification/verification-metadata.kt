package org.gradle.schema.dependency_verification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "verification-metadataType")
public data class `verification-metadata`(
  @SerialName(value = "configuration")
  public val configuration: configuration,
  @SerialName(value = "components")
  public val components: components,
)
