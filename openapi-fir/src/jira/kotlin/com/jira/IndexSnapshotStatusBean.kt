package com.jira

import kotlin.Boolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexSnapshotStatusBean")
public data class IndexSnapshotStatusBean(
  public val running: Boolean? = null,
)
