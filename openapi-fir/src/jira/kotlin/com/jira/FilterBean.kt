package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class FilterBean(
  public val description: String? = null,
  public val editable: Boolean? = null,
  public val favourite: Boolean? = null,
  public val id: String? = null,
  public val jql: String? = null,
  public val name: String? = null,
  public val owner: UserBean? = null,
  public val searchUrl: String? = null,
  public val self: String? = null,
  public val sharePermissions: List<FilterPermissionBean> = emptyList(),
  public val sharedUsers: UserBeanListWrapper? = null,
  public val viewUrl: String? = null,
)
