package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BooleanSettingBean")
public data class BooleanSettingBean(
  public val `value`: Boolean? = null,
)
