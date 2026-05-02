package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class VersionMoveBean(
  public val after: String? = null,
  public val position: VersionMoveBeanPosition? = null,
)
