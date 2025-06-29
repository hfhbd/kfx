package com.sap.hci.api

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomTagsConfiguration-create")
public data class CustomTagsConfigurationCreate(
  public val customTagsConfiguration: String? = null,
)
