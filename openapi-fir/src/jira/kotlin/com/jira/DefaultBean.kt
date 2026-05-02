package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class DefaultBean(
  public val updateDraftIfNeeded: Boolean? = null,
  public val workflow: String? = null,
)
