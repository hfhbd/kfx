package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Icon")
public data class Icon(
  public val link: String? = null,
  public val title: String? = null,
  public val url16x16: String? = null,
)
