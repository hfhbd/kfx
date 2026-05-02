package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "HistoryMetadataParticipant")
public data class HistoryMetadataParticipant(
  public val avatarUrl: String? = null,
  public val displayName: String? = null,
  public val displayNameKey: String? = null,
  public val id: String? = null,
  public val type: String? = null,
  public val url: String? = null,
)
