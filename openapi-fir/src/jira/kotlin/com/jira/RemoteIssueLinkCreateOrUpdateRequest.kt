package com.jira

import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class RemoteIssueLinkCreateOrUpdateRequest(
  public val application: Application? = null,
  public val globalId: String? = null,
  public val `object`: RemoteObject? = null,
  public val relationship: String? = null,
)
