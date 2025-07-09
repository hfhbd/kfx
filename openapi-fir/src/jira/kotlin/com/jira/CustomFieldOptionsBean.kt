package com.jira

import kotlin.Int
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomFieldOptionsBean")
public data class CustomFieldOptionsBean(
  public val options: List<CustomFieldOptionBean> = emptyList(),
  public val total: Int? = null,
)
