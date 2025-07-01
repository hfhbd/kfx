package com.jira

import kotlin.Long
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IdBean")
public data class IdBean(
  public val id: Long? = null,
)
