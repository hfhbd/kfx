package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueLinks")
public data class IssueLinks(
  public val id: String? = null,
  public val inwardIssue: IssueRefJsonBean? = null,
  public val outwardIssue: IssueRefJsonBean? = null,
  public val self: String? = null,
  public val type: IssueLinkTypeJsonBean? = null,
)
