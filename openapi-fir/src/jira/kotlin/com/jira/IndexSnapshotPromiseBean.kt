package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class IndexSnapshotPromiseBean(
  public val futureAbsolutePath: String? = null,
)
