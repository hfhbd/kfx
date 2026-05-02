package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class CommentsWithPaginationJsonBean(
  public val comments: List<CommentJsonBean> = emptyList(),
  public val maxResults: Int? = null,
  public val startAt: Int? = null,
  public val total: Int? = null,
)
