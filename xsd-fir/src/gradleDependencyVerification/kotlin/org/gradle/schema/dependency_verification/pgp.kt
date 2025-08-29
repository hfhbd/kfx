package org.gradle.schema.dependency_verification

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "pgpType")
public data class pgp(
  @SerialName(value = "value")
  public val `value`: String,
)
