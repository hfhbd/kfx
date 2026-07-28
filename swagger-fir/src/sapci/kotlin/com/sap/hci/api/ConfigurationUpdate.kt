package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ConfigurationUpdate")
public data class ConfigurationUpdate(
  public val ParameterValue: String? = null,
  public val DataType: String? = null,
)
