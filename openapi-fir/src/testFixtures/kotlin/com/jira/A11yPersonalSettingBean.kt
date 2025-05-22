package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "A11yPersonalSettingBean")
public data class A11yPersonalSettingBean(
  public val enabled: Boolean? = null,
  public val key: String? = null,
)
