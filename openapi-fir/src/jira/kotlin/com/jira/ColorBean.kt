package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ColorBean")
public data class ColorBean(
  public val key: ColorBeanKey? = null,
)
