package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssuePickerResult")
public data class IssuePickerResult(
  public val sections: List<IssueSection>,
)
