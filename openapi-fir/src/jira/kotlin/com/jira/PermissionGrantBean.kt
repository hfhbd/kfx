package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class PermissionGrantBean(
  public val holder: PermissionHolderBean? = null,
  public val id: Long? = null,
  public val permission: String? = null,
  public val self: String? = null,
)
