package com.sap.hci.api

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomTagsConfigurationCustomTagsConfigurationItems")
public data class CustomTagsConfigurationCustomTagsConfigurationItems(
  public val tagName: String,
  public val isMandatory: Boolean,
)
