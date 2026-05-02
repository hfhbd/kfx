package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class PermissionSchemeBean(
  public val description: String? = null,
  public val expand: String? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val permissions: List<PermissionGrantBean> = emptyList(),
  public val self: String? = null,
)
