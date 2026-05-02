package com.jira

import kotlin.Boolean
import kotlinx.serialization.Serializable

@Serializable
public data class IndexSnapshotStatusBean(
  public val running: Boolean? = null,
)
