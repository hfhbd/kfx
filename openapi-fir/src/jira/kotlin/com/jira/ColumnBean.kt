package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ColumnBean(
  public val max: Int? = null,
  public val min: Int? = null,
  public val name: String? = null,
  public val statuses: List<RelationBean> = emptyList(),
)
