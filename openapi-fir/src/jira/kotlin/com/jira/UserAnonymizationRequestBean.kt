package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UserAnonymizationRequestBean")
public data class UserAnonymizationRequestBean(
  public val newOwnerKey: String? = null,
  public val userKey: String? = null,
)
