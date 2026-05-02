package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DefaultBean")
public data class DefaultBean(
  public val updateDraftIfNeeded: Boolean? = null,
  public val workflow: String? = null,
)
