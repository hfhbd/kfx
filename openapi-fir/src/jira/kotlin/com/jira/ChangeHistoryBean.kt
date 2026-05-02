package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class ChangeHistoryBean(
  public val author: UserJsonBean? = null,
  public val created: Instant? = null,
  public val historyMetadata: HistoryMetadata? = null,
  public val id: String? = null,
  public val items: List<ChangeItemBean> = emptyList(),
)
