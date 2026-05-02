package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class VisibilityJsonBean(
  public val type: VisibilityJsonBeanType? = null,
  public val `value`: String? = null,
)
