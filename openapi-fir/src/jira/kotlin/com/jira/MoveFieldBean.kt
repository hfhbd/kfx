package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class MoveFieldBean(
  public val after: String? = null,
  public val position: MoveFieldBeanPosition? = null,
)
