package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomFieldOptionsBean")
public data class CustomFieldOptionsBean(
  public val options: List<CustomFieldOptionBean>,
  public val total: Int? = null,
)
