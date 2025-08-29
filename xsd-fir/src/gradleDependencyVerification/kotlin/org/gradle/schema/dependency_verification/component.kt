package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "componentType")
public data class component(
  @SerialName(value = "artifact")
  public val artifact: List<artifact> = emptyList(),
)
