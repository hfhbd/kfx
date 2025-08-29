package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "sha512Type")
public data class sha512(
  @SerialName(value = "also-trust")
  public val `also-trust`: List<`also-trust`> = emptyList(),
)
