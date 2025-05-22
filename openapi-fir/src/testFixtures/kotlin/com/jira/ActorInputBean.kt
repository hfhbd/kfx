package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ActorInputBean")
public data class ActorInputBean(
  public val group: List<String>,
  public val user: List<String>,
)
