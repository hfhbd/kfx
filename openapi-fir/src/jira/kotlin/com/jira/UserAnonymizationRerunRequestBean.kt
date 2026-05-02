package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class UserAnonymizationRerunRequestBean(
  public val newOwnerKey: String? = null,
  public val oldUserKey: String? = null,
  public val oldUserName: String? = null,
  public val userKey: String? = null,
)
