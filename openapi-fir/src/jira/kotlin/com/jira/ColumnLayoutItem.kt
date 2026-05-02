package com.jira

import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class ColumnLayoutItem(
  public val columnHeadingKey: String? = null,
  public val id: String? = null,
  public val navigableField: NavigableField? = null,
  public val position: Int? = null,
)
