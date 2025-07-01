package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ColumnBean")
public data class ColumnBean(
  public val max: Int? = null,
  public val min: Int? = null,
  public val name: String? = null,
  public val statuses: List<RelationBean>,
)
