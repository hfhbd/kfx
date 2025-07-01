package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ChangeItemBean")
public data class ChangeItemBean(
  public val `field`: String? = null,
  public val fieldtype: String? = null,
  public val from: String? = null,
  public val fromString: String? = null,
  public val to: String? = null,
  public val toString: String? = null,
)
