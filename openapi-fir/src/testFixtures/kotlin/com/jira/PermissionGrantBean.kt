package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionGrantBean")
public data class PermissionGrantBean(
  public val holder: PermissionHolderBean? = null,
  public val id: Long? = null,
  public val permission: String? = null,
  public val self: String? = null,
)
