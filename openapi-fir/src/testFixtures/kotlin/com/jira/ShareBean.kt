package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ShareBean")
public data class ShareBean(
  public val emails: List<String>,
  public val jql: String? = null,
  public val message: String? = null,
  public val usernames: List<String>,
)
