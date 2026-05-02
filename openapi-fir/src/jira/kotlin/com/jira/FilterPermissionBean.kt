package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FilterPermissionBean")
public data class FilterPermissionBean(
  public val edit: Boolean? = null,
  public val group: GroupJsonBean? = null,
  public val id: Long? = null,
  public val project: ProjectBean? = null,
  public val role: ProjectRoleBean? = null,
  public val type: String? = null,
  public val user: UserBean? = null,
  public val view: Boolean? = null,
)
