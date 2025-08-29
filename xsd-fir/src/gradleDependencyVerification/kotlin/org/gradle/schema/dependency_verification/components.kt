package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "componentsType")
public data class components(
  @SerialName(value = "component")
  public val component: List<component> = emptyList(),
)
