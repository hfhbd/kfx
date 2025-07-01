package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Definition")
public data class Definition(
  public val Url: String? = null,
  public val Name: String? = null,
)
