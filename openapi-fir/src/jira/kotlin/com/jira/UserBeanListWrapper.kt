package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class UserBeanListWrapper(
  public val backingListSize: Int? = null,
  public val callback: ListWrapperCallbackUserBean? = null,
  public val items: List<UserBean> = emptyList(),
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackUserBean? = null,
  public val size: Int? = null,
)
