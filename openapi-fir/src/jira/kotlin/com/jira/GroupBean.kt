package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class GroupBean(
  public val name: String? = null,
  public val self: String? = null,
  public val users: PagedListWrapperUserJsonBeanApplicationUser? = null,
)
