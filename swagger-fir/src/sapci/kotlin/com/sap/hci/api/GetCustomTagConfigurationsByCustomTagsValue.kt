package com.sap.hci.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetCustomTagConfigurationsByCustomTagsValue")
public data class GetCustomTagConfigurationsByCustomTagsValue(
  public val `value`: CustomTagsConfiguration? = null,
)
