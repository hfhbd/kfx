package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AddReactionRequestBean(
  public val commentId: Long? = null,
  public val emojiId: String? = null,
)
