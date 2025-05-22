package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomTagsConfiguration")
public data class CustomTagsConfiguration(
  public val customTagsConfiguration: CustomTagsConfigurationCustomTagsConfiguration,
)
