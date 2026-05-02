package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class AttachmentMetaBean(
  public val enabled: Boolean? = null,
  /**
   * Upload limit in bytes
   */
  public val uploadLimit: Long? = null,
)
