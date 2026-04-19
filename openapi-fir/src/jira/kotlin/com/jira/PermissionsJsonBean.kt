package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionsJsonBean")
public data class PermissionsJsonBean(
  /**
   * A map of permission keys to permission objects.
   */
  public val permissions: Map<String, PermissionJsonBean> = emptyMap(),
)
