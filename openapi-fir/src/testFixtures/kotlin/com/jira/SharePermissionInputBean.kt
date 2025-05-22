package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SharePermissionInputBean")
public data class SharePermissionInputBean(
  public val edit: Boolean? = null,
  public val groupname: String? = null,
  public val projectId: String? = null,
  public val projectRoleId: String? = null,
  public val type: String? = null,
  public val userKey: String? = null,
  public val view: Boolean? = null,
)
