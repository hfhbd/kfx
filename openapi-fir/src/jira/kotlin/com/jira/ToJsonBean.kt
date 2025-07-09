package com.jira

import kotlin.Boolean
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ToJsonBean")
public data class ToJsonBean(
  public val assignee: Boolean? = null,
  public val groups: List<GroupJsonBean> = emptyList(),
  public val reporter: Boolean? = null,
  public val users: List<UserJsonBean> = emptyList(),
  public val voters: Boolean? = null,
  public val watchers: Boolean? = null,
)
