package com.jira

import kotlin.Long
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AffectedEntityBean(
  public val description: String? = null,
  public val numberOfOccurrences: Long? = null,
  public val type: AffectedEntityBeanType? = null,
  public val uri: String? = null,
  public val uriDisplayName: String? = null,
)
