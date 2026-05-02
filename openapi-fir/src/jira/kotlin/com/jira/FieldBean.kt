package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class FieldBean(
  public val clauseNames: List<String> = emptyList(),
  public val custom: Boolean? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val navigable: Boolean? = null,
  public val orderable: Boolean? = null,
  public val schema: JsonTypeBean? = null,
  public val searchable: Boolean? = null,
)
