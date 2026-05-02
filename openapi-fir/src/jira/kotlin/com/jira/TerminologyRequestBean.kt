package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class TerminologyRequestBean(
  public val newName: String? = null,
  public val newNamePlural: String? = null,
  public val originalName: String? = null,
)
