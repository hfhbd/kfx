package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Worklog")
public data class Worklog(
  public val author: UserJsonBean? = null,
  public val comment: String? = null,
  public val created: String? = null,
  public val id: String? = null,
  public val issueId: String? = null,
  public val self: String? = null,
  public val started: String? = null,
  public val timeSpent: String? = null,
  public val timeSpentSeconds: Long? = null,
  public val updateAuthor: UserJsonBean? = null,
  public val updated: String? = null,
  public val visibility: VisibilityJsonBean? = null,
)
