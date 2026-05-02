package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class RemoteObject(
  public val icon: Icon? = null,
  public val status: Status? = null,
  public val summary: String? = null,
  public val title: String? = null,
  public val url: String? = null,
)
