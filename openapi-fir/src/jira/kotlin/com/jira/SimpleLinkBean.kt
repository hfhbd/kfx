package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class SimpleLinkBean(
  public val href: String? = null,
  public val iconClass: String? = null,
  public val id: String? = null,
  public val label: String? = null,
  public val params: Map<String, String> = emptyMap(),
  public val styleClass: String? = null,
  public val title: String? = null,
  public val weight: Int? = null,
)
