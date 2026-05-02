package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

/**
 * A map of permission keys to permission objects.
 */
@Serializable
public data class PermissionJsonBean(
  public val description: String? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val name: String? = null,
  public val type: PermissionJsonBeanType? = null,
)
