package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RemoteEntityLinkJsonBean")
public data class RemoteEntityLinkJsonBean(
  public val link: String? = null,
  public val name: String? = null,
  public val self: String? = null,
)
