package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EpicBean")
public data class EpicBean(
  public val color: ColorBean? = null,
  public val done: Boolean? = null,
  public val id: Long? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val summary: String? = null,
)
