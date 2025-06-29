package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectRoleBean")
public data class ProjectRoleBean(
  public val actors: List<RoleActorBean>,
  public val description: String? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val self: String? = null,
)
