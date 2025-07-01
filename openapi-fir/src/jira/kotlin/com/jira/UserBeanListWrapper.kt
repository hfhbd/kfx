package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserBeanListWrapper")
public data class UserBeanListWrapper(
  public val backingListSize: Int? = null,
  public val callback: ListWrapperCallbackUserBean? = null,
  public val items: List<UserBean>,
  public val maxResults: Int? = null,
  public val pagingCallback: ListWrapperCallbackUserBean? = null,
  public val size: Int? = null,
)
