package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Resource-update")
public data class ResourceUpdate(
  public val ResourceContent: String? = null,
)
