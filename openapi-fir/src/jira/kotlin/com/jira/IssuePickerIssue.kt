package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssuePickerIssue")
public data class IssuePickerIssue(
  public val img: String? = null,
  public val key: String? = null,
  public val keyHtml: String? = null,
  public val summary: String? = null,
  public val summaryText: String? = null,
)
