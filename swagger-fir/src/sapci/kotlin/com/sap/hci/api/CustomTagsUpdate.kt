package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomTagsUpdate")
public data class CustomTagsUpdate(
  public val Value: String? = null,
)
