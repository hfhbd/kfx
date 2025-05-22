package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PermissionGrantsBean")
public data class PermissionGrantsBean(
  public val expand: String? = null,
  public val permissions: List<PermissionGrantBean>,
)
