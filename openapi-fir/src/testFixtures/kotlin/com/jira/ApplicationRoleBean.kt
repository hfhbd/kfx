package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ApplicationRoleBean")
public data class ApplicationRoleBean(
  public val defaultGroups: List<String>,
  public val defined: Boolean? = null,
  public val groups: List<String>,
  public val hasUnlimitedSeats: Boolean? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val numberOfSeats: Int? = null,
  public val platform: Boolean? = null,
  public val remainingSeats: Int? = null,
  public val selectedByDefault: Boolean? = null,
  public val userCount: Int? = null,
  public val userCountDescription: String? = null,
)
