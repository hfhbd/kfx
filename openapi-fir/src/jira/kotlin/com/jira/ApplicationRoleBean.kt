package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ApplicationRoleBean")
public data class ApplicationRoleBean(
  public val defaultGroups: Set<String>? = emptySet(),
  public val defined: Boolean? = null,
  public val groups: Set<String>? = emptySet(),
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
