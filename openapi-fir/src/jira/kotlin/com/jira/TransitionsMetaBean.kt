package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "TransitionsMetaBean")
public data class TransitionsMetaBean(
  public val transitions: List<TransitionBean>,
)
