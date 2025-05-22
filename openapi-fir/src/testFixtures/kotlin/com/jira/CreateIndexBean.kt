package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CreateIndexBean")
public data class CreateIndexBean(
  public val entityName: String? = null,
  public val fieldNameToColumnName: Unit? = null,
  public val indexName: String? = null,
  public val tableName: String? = null,
  public val unique: Boolean? = null,
)
