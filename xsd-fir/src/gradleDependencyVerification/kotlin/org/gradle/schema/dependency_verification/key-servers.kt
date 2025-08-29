package org.gradle.schema.dependency_verification

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "key-serversType")
public data class `key-servers`(
  @SerialName(value = "key-server")
  public val `key-server`: List<`key-server`> = emptyList(),
)
