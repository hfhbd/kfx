package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomFieldDefinitionJsonBean")
public data class CustomFieldDefinitionJsonBean(
  public val description: String? = null,
  public val id: String? = null,
  public val issueTypeIds: List<String>,
  public val name: String? = null,
  public val projectIds: List<Long>,
  public val searcherKey: String? = null,
  public val self: String? = null,
  public val type: String? = null,
)
