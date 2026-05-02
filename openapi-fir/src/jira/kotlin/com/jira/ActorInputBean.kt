package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ActorInputBean(
  public val group: List<String> = emptyList(),
  public val user: List<String> = emptyList(),
)
