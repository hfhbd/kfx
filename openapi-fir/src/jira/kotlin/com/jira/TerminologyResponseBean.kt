package com.jira

import kotlin.Boolean
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "TerminologyResponseBean")
public data class TerminologyResponseBean(
  public val isDefault: Boolean? = null,
  public val newName: String? = null,
  public val newNamePlural: String? = null,
  public val originalName: String? = null,
  public val originalNamePlural: String? = null,
)
