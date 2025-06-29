package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "Gadget")
public data class Gadget(
  public val filterId: Long? = null,
  public val filterName: String? = null,
  public val gadgetUri: String? = null,
  public val jql: String? = null,
  public val portalId: Long? = null,
  public val userPrefs: Map<String, String>? = null,
)
