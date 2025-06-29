package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Resource")
public data class Resource(
  public val Name: String? = null,
  public val ResourceType: String? = null,
  public val ReferencedResourceType: String? = null,
  public val ResourceContent: String? = null,
)
