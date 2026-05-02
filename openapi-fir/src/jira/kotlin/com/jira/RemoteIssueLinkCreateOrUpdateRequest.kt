package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "RemoteIssueLinkCreateOrUpdateRequest")
public data class RemoteIssueLinkCreateOrUpdateRequest(
  public val application: Application? = null,
  public val globalId: String? = null,
  public val `object`: RemoteObject? = null,
  public val relationship: String? = null,
)
