package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AvatarBean")
public data class AvatarBean(
  public val id: String? = null,
  public val owner: String? = null,
  public val selected: Boolean? = null,
)
