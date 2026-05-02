package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class VersionUsageInCustomFields(
  public val customFieldId: Long? = null,
  public val fieldName: String? = null,
  public val issueCountWithVersionInCustomField: Long? = null,
)
