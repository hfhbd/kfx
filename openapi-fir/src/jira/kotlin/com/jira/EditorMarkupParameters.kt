package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EditorMarkupParameters")
public data class EditorMarkupParameters(
  public val fieldId: String,
  public val fieldName: String? = null,
  public val issueKey: String? = null,
  public val `value`: String? = null,
)
