package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomFieldReplacement")
public data class CustomFieldReplacement(
  public val customFieldId: Long? = null,
  public val moveTo: Long? = null,
)
