package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PageBeanProjectBean")
public data class PageBeanProjectBean(
  public val isLast: Boolean? = null,
  public val maxResults: Int? = null,
  public val nextPage: String? = null,
  public val self: String? = null,
  public val startAt: Long? = null,
  public val total: Long? = null,
  public val values: List<ProjectBean>,
)
