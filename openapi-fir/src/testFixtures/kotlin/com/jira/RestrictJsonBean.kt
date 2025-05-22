package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RestrictJsonBean")
public data class RestrictJsonBean(
  public val groups: List<GroupJsonBean>,
  public val permissions: List<PermissionJsonBean>,
)
