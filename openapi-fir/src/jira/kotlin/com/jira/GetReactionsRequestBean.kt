package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "GetReactionsRequestBean")
public data class GetReactionsRequestBean(
  public val commentIds: List<Long>,
)
