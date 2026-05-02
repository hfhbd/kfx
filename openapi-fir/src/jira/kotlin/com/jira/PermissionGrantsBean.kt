package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class PermissionGrantsBean(
  public val expand: String? = null,
  public val permissions: List<PermissionGrantBean> = emptyList(),
)
