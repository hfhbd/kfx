package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "ApplicationPropertyBean")
public data class ApplicationPropertyBean(
  public val id: String? = null,
  public val `value`: String? = null,
)
