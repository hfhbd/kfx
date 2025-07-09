package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionsInputBean")
public data class PermissionsInputBean(
  public val grants: List<GrantToPermissionInputBean> = emptyList(),
  public val permissionKeys: List<String> = emptyList(),
)
