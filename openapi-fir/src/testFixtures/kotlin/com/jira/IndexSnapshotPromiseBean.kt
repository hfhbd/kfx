package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexSnapshotPromiseBean")
public data class IndexSnapshotPromiseBean(
  public val futureAbsolutePath: String? = null,
)
