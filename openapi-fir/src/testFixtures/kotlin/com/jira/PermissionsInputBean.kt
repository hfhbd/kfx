package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionsInputBean")
public data class PermissionsInputBean(
  public val grants: List<GrantToPermissionInputBean>,
  public val permissionKeys: List<String>,
)
