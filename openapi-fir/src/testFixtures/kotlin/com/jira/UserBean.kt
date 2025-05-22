package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserBean")
public data class UserBean(
  public val active: Boolean? = null,
  public val applicationRoles: SimpleListWrapperApplicationRoleBean? = null,
  public val avatarUrls: Unit? = null,
  public val deleted: Boolean? = null,
  public val displayName: String? = null,
  public val emailAddress: String? = null,
  public val expand: String? = null,
  public val groups: SimpleListWrapperGroupJsonBean? = null,
  public val key: String? = null,
  public val lastLoginTime: String? = null,
  public val locale: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val timeZone: String? = null,
)
