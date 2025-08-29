package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "trusted-artifactsType")
public data class `trusted-artifacts`(
  @SerialName(value = "trust")
  public val trust: List<trust> = emptyList(),
)
