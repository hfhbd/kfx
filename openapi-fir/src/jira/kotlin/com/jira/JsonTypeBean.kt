package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "JsonTypeBean")
public data class JsonTypeBean(
  public val custom: String? = null,
  public val customId: Long? = null,
  public val items: String? = null,
  public val system: String? = null,
  public val type: String? = null,
)
