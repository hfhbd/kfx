package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "LinkIssueRequestJsonBean")
public data class LinkIssueRequestJsonBean(
  public val comment: CommentJsonBean? = null,
  public val inwardIssue: IssueRefJsonBean? = null,
  public val outwardIssue: IssueRefJsonBean? = null,
  public val type: IssueLinkTypeJsonBean? = null,
)
