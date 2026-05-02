package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AddFieldBean(
  public val fieldId: String? = null,
)
