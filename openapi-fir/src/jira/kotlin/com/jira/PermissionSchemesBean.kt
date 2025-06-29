package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionSchemesBean")
public data class PermissionSchemesBean(
  public val permissionSchemes: List<PermissionSchemeBean>,
)
