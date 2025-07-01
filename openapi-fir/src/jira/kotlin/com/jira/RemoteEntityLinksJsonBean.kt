package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RemoteEntityLinksJsonBean")
public data class RemoteEntityLinksJsonBean(
  public val links: List<RemoteEntityLinkJsonBean>,
)
