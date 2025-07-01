package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PinnedCommentJsonBean")
public data class PinnedCommentJsonBean(
  public val comment: CommentJsonBean? = null,
  public val pinnedBy: String? = null,
  public val pinnedDate: String? = null,
)
