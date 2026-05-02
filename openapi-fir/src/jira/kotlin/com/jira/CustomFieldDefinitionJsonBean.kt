package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class CustomFieldDefinitionJsonBean(
  public val description: String? = null,
  public val id: String? = null,
  public val issueTypeIds: List<String> = emptyList(),
  public val name: String? = null,
  public val projectIds: List<Long> = emptyList(),
  public val searcherKey: String? = null,
  public val self: String? = null,
  public val type: String? = null,
)
