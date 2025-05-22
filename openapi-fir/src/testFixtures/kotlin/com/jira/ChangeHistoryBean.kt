package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ChangeHistoryBean")
public data class ChangeHistoryBean(
  public val author: UserJsonBean? = null,
  public val created: Instant? = null,
  public val historyMetadata: HistoryMetadata? = null,
  public val id: String? = null,
  public val items: List<ChangeItemBean>,
)
