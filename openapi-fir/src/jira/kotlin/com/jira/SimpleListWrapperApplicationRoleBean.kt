package com.jira

import kotlin.Int
import kotlinx.serialization.Serializable

@Serializable
public data class SimpleListWrapperApplicationRoleBean(
  public val callback: ListWrapperCallbackApplicationRoleBean? = null,
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackApplicationRoleBean? = null,
  public val size: Int? = null,
)
