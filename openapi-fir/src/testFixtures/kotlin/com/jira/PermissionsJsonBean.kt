package com.jira

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionsJsonBean")
public data class PermissionsJsonBean(
  /**
   * A map of permission keys to permission objects.
   */
  public val permissions: Unit? = null,
)
