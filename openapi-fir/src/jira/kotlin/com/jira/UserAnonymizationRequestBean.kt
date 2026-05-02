package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class UserAnonymizationRequestBean(
  public val newOwnerKey: String? = null,
  public val userKey: String? = null,
)
