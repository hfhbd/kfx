package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BulkDeleteResponseBean")
public data class BulkDeleteResponseBean(
  public val deletedCustomFields: List<String> = emptyList(),
  public val message: String? = null,
  public val notDeletedCustomFields: Map<String, String> = emptyMap(),
)
