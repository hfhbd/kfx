package com.jira

import kotlin.Long
import kotlinx.serialization.Serializable

@Serializable
public data class CustomFieldReplacement(
  public val customFieldId: Long? = null,
  public val moveTo: Long? = null,
)
