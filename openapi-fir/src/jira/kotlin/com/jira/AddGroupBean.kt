package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AddGroupBean")
public data class AddGroupBean(
  public val name: String? = null,
)
