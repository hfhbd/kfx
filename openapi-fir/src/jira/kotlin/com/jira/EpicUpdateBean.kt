package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EpicUpdateBean")
public data class EpicUpdateBean(
  public val color: ColorBean? = null,
  public val done: Boolean? = null,
  public val name: String? = null,
  public val summary: String? = null,
)
