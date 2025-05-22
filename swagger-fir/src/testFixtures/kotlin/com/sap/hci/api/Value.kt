package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Value")
public data class Value(
  public val SrcValue: String? = null,
  public val TgtValue: String? = null,
)
