package com.jira

import kotlin.Long
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ProjectRoleActorsUpdateBean")
public data class ProjectRoleActorsUpdateBean(
  public val categorisedActors: Unit? = null,
  public val id: Long? = null,
)
