package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SetColumns_1Request")
public data class SetColumns_1Request(
  public val columns: List<String> = emptyList(),
)
