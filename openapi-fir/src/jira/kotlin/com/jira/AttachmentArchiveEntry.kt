package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AttachmentArchiveEntry")
public data class AttachmentArchiveEntry(
  public val abbreviatedName: String? = null,
  public val entryIndex: Long? = null,
  public val mediaType: String? = null,
  public val name: String? = null,
  public val size: Long? = null,
)
