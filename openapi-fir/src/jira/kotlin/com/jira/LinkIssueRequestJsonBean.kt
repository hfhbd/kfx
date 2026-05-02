package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class LinkIssueRequestJsonBean(
  public val comment: CommentJsonBean? = null,
  public val inwardIssue: IssueRefJsonBean? = null,
  public val outwardIssue: IssueRefJsonBean? = null,
  public val type: IssueLinkTypeJsonBean? = null,
)
