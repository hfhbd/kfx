package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class CreateIndexBean(
  public val entityName: String? = null,
  public val fieldNameToColumnName: Map<String, String> = emptyMap(),
  public val indexName: String? = null,
  public val tableName: String? = null,
  public val unique: Boolean? = null,
)
