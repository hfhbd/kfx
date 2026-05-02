package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class ProjectRoleActorsUpdateBean(
  public val categorisedActors: Map<String, List<String>> = emptyMap(),
  public val id: Long? = null,
)
