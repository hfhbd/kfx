package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class CreateUpdateRoleRequestBean(
  public val description: String? = null,
  public val name: String? = null,
)
