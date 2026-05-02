package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class FieldEditBean(
  public val `value`: String? = null,
)
