package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Configuration")
public data class Configuration(
  public val ParameterKey: String? = null,
  public val ParameterValue: String? = null,
  public val DataType: String? = null,
)
