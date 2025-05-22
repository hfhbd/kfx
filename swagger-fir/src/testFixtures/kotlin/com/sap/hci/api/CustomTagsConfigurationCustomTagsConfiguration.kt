package com.sap.hci.api

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomTagsConfigurationCustomTagsConfiguration")
public data class CustomTagsConfigurationCustomTagsConfiguration(
  public val tagName: String,
  public val isMandatory: Boolean,
)
