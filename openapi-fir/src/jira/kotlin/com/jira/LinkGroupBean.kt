package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "LinkGroupBean")
public data class LinkGroupBean(
  public val groups: List<LinkGroupBean> = emptyList(),
  public val `header`: SimpleLinkBean? = null,
  public val id: String? = null,
  public val links: List<SimpleLinkBean> = emptyList(),
  public val styleClass: String? = null,
  public val weight: Int? = null,
)
