package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AttachmentArchiveImpl")
public data class AttachmentArchiveImpl(
  public val entries: List<AttachmentArchiveEntry>,
  /**
   * Total number of entries available (can be larger that what was asked for)
   */
  public val totalEntryCount: Int? = null,
)
