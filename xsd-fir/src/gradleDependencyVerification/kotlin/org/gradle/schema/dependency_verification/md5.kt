package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "md5Type")
public data class md5(
  @SerialName(value = "also-trust")
  public val `also-trust`: List<`also-trust`> = emptyList(),
)
