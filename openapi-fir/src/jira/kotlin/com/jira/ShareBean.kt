package com.jira

import kotlin.String
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ShareBean")
public data class ShareBean(
  public val emails: Set<String>? = emptySet(),
  public val jql: String? = null,
  public val message: String? = null,
  public val usernames: Set<String>? = emptySet(),
)
