package org.gradle.schema.dependency_verification

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "configurationType")
public data class configuration(
  @SerialName(value = "verify-metadata")
  public val `verify-metadata`: Boolean,
  @SerialName(value = "verify-signatures")
  public val `verify-signatures`: Boolean,
  @SerialName(value = "keyring-format")
  public val `keyring-format`: String? = null,
  @SerialName(value = "key-servers")
  public val `key-servers`: `key-servers`? = null,
  @SerialName(value = "trusted-artifacts")
  public val `trusted-artifacts`: `trusted-artifacts`? = null,
  @SerialName(value = "ignored-keys")
  public val `ignored-keys`: `ignored-keys`? = null,
  @SerialName(value = "trusted-keys")
  public val `trusted-keys`: `trusted-keys`? = null,
)
