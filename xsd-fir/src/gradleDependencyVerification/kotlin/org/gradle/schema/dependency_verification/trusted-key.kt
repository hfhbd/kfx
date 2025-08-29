package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "trusted-keyType")
public data class `trusted-key`(
  @SerialName(value = "trusting")
  public val trusting: List<trusting> = emptyList(),
)
