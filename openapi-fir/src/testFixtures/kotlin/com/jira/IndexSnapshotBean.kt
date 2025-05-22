package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IndexSnapshotBean")
public data class IndexSnapshotBean(
  public val absolutePath: String? = null,
  public val timestamp: Long? = null,
)
