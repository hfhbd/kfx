package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "PartialSuccessBean")
public data class PartialSuccessBean(
  public val entries: List<Entry>,
)
