package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "EntityVersionBean")
public data class EntityVersionBean(
  public val deleted: Boolean? = null,
  public val entityId: Long? = null,
  public val entityType: String? = null,
  public val entityVersion: Long? = null,
  public val hasVersion: Boolean? = null,
  public val parentIssueId: Long? = null,
  public val updateTime: Instant? = null,
)
