package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ColumnLayout")
public data class ColumnLayout(
  public val columnConfig: ColumnLayoutColumnConfig? = null,
  public val columnLayoutItems: List<ColumnLayoutItem> = emptyList(),
)
