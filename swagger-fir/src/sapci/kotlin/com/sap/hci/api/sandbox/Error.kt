package com.sap.hci.api.sandbox

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Error")
public data class Error(
  public val code: String? = null,
  public val message: String? = null,
)
