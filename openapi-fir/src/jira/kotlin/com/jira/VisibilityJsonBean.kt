package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VisibilityJsonBean")
public data class VisibilityJsonBean(
  public val type: VisibilityJsonBeanType? = null,
  public val `value`: String? = null,
)
