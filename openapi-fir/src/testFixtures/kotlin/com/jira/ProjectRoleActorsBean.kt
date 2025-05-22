package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectRoleActorsBean")
public data class ProjectRoleActorsBean(
  public val actors: List<RoleActorBean>,
)
