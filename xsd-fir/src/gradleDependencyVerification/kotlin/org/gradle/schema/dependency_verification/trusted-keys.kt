package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "trusted-keysType")
public data class `trusted-keys`(
  @SerialName(value = "trusted-key")
  public val `trusted-key`: List<`trusted-key`> = emptyList(),
)
