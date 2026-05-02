package com.jira

import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class HumanReadableArchive(
  public val entries: Unit? = null,
  public val id: Long? = null,
  public val mediaType: String? = null,
  public val name: String? = null,
  public val totalEntryCount: Long? = null,
)
