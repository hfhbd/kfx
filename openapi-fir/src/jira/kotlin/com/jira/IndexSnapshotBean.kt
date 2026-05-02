package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IndexSnapshotBean(
  public val absolutePath: String? = null,
  public val timestamp: Long? = null,
)
