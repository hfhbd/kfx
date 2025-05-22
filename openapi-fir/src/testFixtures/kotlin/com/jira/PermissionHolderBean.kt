package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionHolderBean")
public data class PermissionHolderBean(
  public val `field`: FieldBean? = null,
  public val group: GroupJsonBean? = null,
  public val parameter: String? = null,
  public val projectRole: ProjectRoleBean? = null,
  public val type: String? = null,
  public val user: UserJsonBean? = null,
)
