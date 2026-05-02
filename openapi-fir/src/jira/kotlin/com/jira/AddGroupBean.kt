package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class AddGroupBean(
  public val name: String? = null,
)
