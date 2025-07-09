package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AutoCompleteResponseBean")
public data class AutoCompleteResponseBean(
  public val jqlReservedWords: List<String> = emptyList(),
  public val visibleFieldNames: List<String> = emptyList(),
  public val visibleFunctionNames: List<String> = emptyList(),
)
