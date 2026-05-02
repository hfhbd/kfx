package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class PinnedCommentJsonBean(
  public val comment: CommentJsonBean? = null,
  public val pinnedBy: String? = null,
  public val pinnedDate: String? = null,
)
