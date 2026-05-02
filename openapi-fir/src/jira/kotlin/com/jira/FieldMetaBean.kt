package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class FieldMetaBean(
  public val allowedValues: List<String> = emptyList(),
  public val autoCompleteUrl: String? = null,
  public val defaultValue: Unit? = null,
  public val fieldId: String? = null,
  public val hasDefaultValue: Boolean? = null,
  public val name: String? = null,
  public val operations: List<String> = emptyList(),
  public val required: Boolean? = null,
  public val schema: JsonTypeBean? = null,
)
