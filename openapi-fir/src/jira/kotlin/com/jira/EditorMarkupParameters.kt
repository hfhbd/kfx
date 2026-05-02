package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class EditorMarkupParameters(
  public val fieldId: String,
  public val fieldName: String? = null,
  public val issueKey: String? = null,
  public val `value`: String? = null,
)
