package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CommentsWithPaginationJsonBean")
public data class CommentsWithPaginationJsonBean(
  public val comments: List<CommentJsonBean>,
  public val maxResults: Int? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
)
