package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IssueLinksResponse(
  public val id: String? = null,
  public val inwardIssue: IssueRefJsonBean? = null,
  public val outwardIssue: IssueRefJsonBean? = null,
  public val self: String? = null,
  public val type: IssueLinkTypeJsonBean? = null,
)
