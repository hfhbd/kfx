package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "UpdateUserToGroupBean")
public data class UpdateUserToGroupBean(
  public val name: String? = null,
)
