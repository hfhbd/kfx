package com.jira

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "BulkDeleteResponseBean")
public data class BulkDeleteResponseBean(
  public val deletedCustomFields: List<String>,
  public val message: String? = null,
  public val notDeletedCustomFields: Unit? = null,
)
