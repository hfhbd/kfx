package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ColumnConfigBean")
public data class ColumnConfigBean(
  public val columns: List<ColumnBean>,
  public val constraintType: String? = null,
)
