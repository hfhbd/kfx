package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RelationBean")
public data class RelationBean(
  public val id: String? = null,
  public val self: String? = null,
)
