package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldValueBean")
public data class FieldValueBean(
  public val fieldId: String? = null,
  public val `value`: Unit? = null,
)
