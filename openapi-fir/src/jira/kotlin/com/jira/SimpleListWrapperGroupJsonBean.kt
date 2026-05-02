package com.jira

import kotlin.Int
import kotlinx.serialization.Serializable

@Serializable
public data class SimpleListWrapperGroupJsonBean(
  public val callback: ListWrapperCallbackGroupJsonBean? = null,
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackGroupJsonBean? = null,
  public val size: Int? = null,
)
