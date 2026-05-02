package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class RestrictJsonBean(
  public val groups: List<GroupJsonBean> = emptyList(),
  public val permissions: List<PermissionJsonBean> = emptyList(),
)
