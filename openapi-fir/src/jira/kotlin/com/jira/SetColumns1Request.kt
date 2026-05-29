package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SetColumns1Request")
public data class SetColumns1Request(
  public val columns: List<String> = emptyList(),
)
