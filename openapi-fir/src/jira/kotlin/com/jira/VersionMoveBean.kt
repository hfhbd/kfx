package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VersionMoveBean")
public data class VersionMoveBean(
  public val after: String? = null,
  public val position: VersionMoveBeanPosition? = null,
)
