package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PagedListWrapperUserJsonBeanApplicationUser")
public data class PagedListWrapperUserJsonBeanApplicationUser(
  public val backingListSize: Int? = null,
  public val callback: ListWrapperCallbackUserJsonBean? = null,
  public val items: List<UserJsonBean>,
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackUserJsonBean? = null,
  public val size: Int? = null,
)
