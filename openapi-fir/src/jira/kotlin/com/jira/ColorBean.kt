package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class ColorBean(
  public val key: ColorBeanKey? = null,
)
