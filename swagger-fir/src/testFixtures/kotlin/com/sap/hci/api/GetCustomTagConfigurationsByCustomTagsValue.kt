package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetCustomTagConfigurations('CustomTags')${'$'}value")
public data class GetCustomTagConfigurationsByCustomTagsValue(
  public val `value`: CustomTagsConfiguration,
)
