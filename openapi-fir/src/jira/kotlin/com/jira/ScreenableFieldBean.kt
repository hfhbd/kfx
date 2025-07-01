package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ScreenableFieldBean")
public data class ScreenableFieldBean(
  public val id: String? = null,
  public val name: String? = null,
  public val showWhenEmpty: Boolean? = null,
  public val type: String? = null,
)
