package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class PagedListWrapperUserJsonBeanApplicationUser(
  public val backingListSize: Int? = null,
  public val callback: ListWrapperCallbackUserJsonBean? = null,
  public val items: List<UserJsonBean> = emptyList(),
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackUserJsonBean? = null,
  public val size: Int? = null,
)
