package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntryPoint")
public data class EntryPoint(
  public val Name: String? = null,
  public val Url: String? = null,
  public val Type: String? = null,
  public val AdditionalInformation: String? = null,
)
