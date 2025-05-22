package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionSchemeAttributeBean")
public data class PermissionSchemeAttributeBean(
  public val key: String? = null,
  public val `value`: String? = null,
)
