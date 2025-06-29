package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AutoCompleteResponseBean")
public data class AutoCompleteResponseBean(
  public val jqlReservedWords: List<String>,
  public val visibleFieldNames: List<String>,
  public val visibleFunctionNames: List<String>,
)
