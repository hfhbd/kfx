package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class AttachmentArchiveImpl(
  public val entries: List<AttachmentArchiveEntry> = emptyList(),
  /**
   * Total number of entries available (can be larger that what was asked for)
   */
  public val totalEntryCount: Int? = null,
)
