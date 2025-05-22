package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AttachmentMetaBean")
public data class AttachmentMetaBean(
  public val enabled: Boolean? = null,
  /**
   * Upload limit in bytes
   */
  public val uploadLimit: Long? = null,
)
