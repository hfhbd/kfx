package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AddFieldBean")
public data class AddFieldBean(
  public val fieldId: String? = null,
)
