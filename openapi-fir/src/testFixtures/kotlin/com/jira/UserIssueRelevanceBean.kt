package com.jira

import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserIssueRelevanceBean")
public data class UserIssueRelevanceBean(
  public val avatarUrls: Map<String, String>? = null,
  public val displayName: String? = null,
  public val emailAddress: String? = null,
  public val highestIssueInvolvementRank: Int? = null,
  public val issueInvolvements: List<IssueInvolvementBean>,
  public val key: String? = null,
  public val latestCommentCreationTime: Long? = null,
  public val name: String? = null,
  public val self: String? = null,
)
