package com.jira

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SimpleListWrapperApplicationRoleBean")
public data class SimpleListWrapperApplicationRoleBean(
  public val callback: ListWrapperCallbackApplicationRoleBean? = null,
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackApplicationRoleBean? = null,
  public val size: Int? = null,
)
