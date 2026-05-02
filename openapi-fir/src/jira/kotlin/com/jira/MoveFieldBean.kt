package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "MoveFieldBean")
public data class MoveFieldBean(
  public val after: String? = null,
  public val position: MoveFieldBeanPosition? = null,
)
