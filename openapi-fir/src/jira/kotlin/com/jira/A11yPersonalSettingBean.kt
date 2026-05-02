package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class A11yPersonalSettingBean(
  public val enabled: Boolean? = null,
  public val key: String? = null,
)
