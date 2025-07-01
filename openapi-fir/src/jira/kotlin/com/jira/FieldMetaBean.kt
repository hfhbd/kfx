package com.jira

import kotlin.Boolean
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldMetaBean")
public data class FieldMetaBean(
  public val allowedValues: List<String>,
  public val autoCompleteUrl: String? = null,
  public val defaultValue: Unit? = null,
  public val fieldId: String? = null,
  public val hasDefaultValue: Boolean? = null,
  public val name: String? = null,
  public val operations: List<String>,
  public val required: Boolean? = null,
  public val schema: JsonTypeBean? = null,
)
