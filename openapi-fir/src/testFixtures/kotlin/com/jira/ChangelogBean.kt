package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ChangelogBean")
public data class ChangelogBean(
  public val histories: List<ChangeHistoryBean>,
  public val maxResults: Int? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
)
