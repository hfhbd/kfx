package com.jira

import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Entry")
public data class Entry(
  public val errors: List<String>,
  public val issueId: Long? = null,
  public val issueKey: String? = null,
  public val status: Int? = null,
)
