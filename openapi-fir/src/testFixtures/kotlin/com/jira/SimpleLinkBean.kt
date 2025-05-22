package com.jira

import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SimpleLinkBean")
public data class SimpleLinkBean(
  public val href: String? = null,
  public val iconClass: String? = null,
  public val id: String? = null,
  public val label: String? = null,
  public val params: Unit? = null,
  public val styleClass: String? = null,
  public val title: String? = null,
  public val weight: Int? = null,
)
