package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ignored-keysType")
public data class `ignored-keys`(
  @SerialName(value = "ignored-key")
  public val `ignored-key`: List<`ignored-key`> = emptyList(),
)
