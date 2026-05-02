package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class FieldValueBean(
  public val fieldId: String? = null,
  public val `value`: Unit? = null,
)
