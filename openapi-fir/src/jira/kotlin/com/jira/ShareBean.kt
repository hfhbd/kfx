package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class ShareBean(
  public val emails: List<String> = emptyList(),
  public val jql: String? = null,
  public val message: String? = null,
  public val usernames: List<String> = emptyList(),
)
