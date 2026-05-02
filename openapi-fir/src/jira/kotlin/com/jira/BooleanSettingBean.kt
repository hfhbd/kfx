package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class BooleanSettingBean(
  public val `value`: Boolean? = null,
)
