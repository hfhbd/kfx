package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldEditBean")
public data class FieldEditBean(
  public val `value`: String? = null,
)
