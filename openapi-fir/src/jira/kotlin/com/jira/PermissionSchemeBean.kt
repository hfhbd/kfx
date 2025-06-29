package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionSchemeBean")
public data class PermissionSchemeBean(
  public val description: String? = null,
  public val expand: String? = null,
  public val id: Long? = null,
  public val name: String? = null,
  public val permissions: List<PermissionGrantBean>,
  public val self: String? = null,
)
