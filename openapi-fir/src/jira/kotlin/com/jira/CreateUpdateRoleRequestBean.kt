package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CreateUpdateRoleRequestBean")
public data class CreateUpdateRoleRequestBean(
  public val description: String? = null,
  public val name: String? = null,
)
