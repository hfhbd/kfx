package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldBean")
public data class FieldBean(
  public val clauseNames: List<String>,
  public val custom: Boolean? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val navigable: Boolean? = null,
  public val orderable: Boolean? = null,
  public val schema: JsonTypeBean? = null,
  public val searchable: Boolean? = null,
)
