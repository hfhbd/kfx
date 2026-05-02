package com.jira

import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class HistoryMetadata(
  public val activityDescription: String? = null,
  public val activityDescriptionKey: String? = null,
  public val actor: HistoryMetadataParticipant? = null,
  public val cause: HistoryMetadataParticipant? = null,
  public val description: String? = null,
  public val descriptionKey: String? = null,
  public val emailDescription: String? = null,
  public val emailDescriptionKey: String? = null,
  public val extraData: Map<String, String> = emptyMap(),
  public val generator: HistoryMetadataParticipant? = null,
  public val type: String? = null,
)
