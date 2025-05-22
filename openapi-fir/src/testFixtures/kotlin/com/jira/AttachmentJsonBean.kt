package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AttachmentJsonBean")
public data class AttachmentJsonBean(
  public val author: UserJsonBean? = null,
  public val content: String? = null,
  public val created: Instant? = null,
  public val filename: String? = null,
  public val id: String? = null,
  public val mimeType: String? = null,
  public val self: String? = null,
  public val size: Long? = null,
  public val thumbnail: String? = null,
)
