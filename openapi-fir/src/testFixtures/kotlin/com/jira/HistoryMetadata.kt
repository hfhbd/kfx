package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "HistoryMetadata")
public data class HistoryMetadata(
  public val activityDescription: String? = null,
  public val activityDescriptionKey: String? = null,
  public val actor: HistoryMetadataParticipant? = null,
  public val cause: HistoryMetadataParticipant? = null,
  public val description: String? = null,
  public val descriptionKey: String? = null,
  public val emailDescription: String? = null,
  public val emailDescriptionKey: String? = null,
  public val extraData: Unit? = null,
  public val generator: HistoryMetadataParticipant? = null,
  public val type: String? = null,
)
