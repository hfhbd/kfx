package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.collections.Set
import kotlin.collections.emptySet
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldBean")
public data class FieldBean(
  public val clauseNames: Set<String>? = emptySet(),
  public val custom: Boolean? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val navigable: Boolean? = null,
  public val orderable: Boolean? = null,
  public val schema: JsonTypeBean? = null,
  public val searchable: Boolean? = null,
)
