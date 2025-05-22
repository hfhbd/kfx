package com.jira

import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CustomFieldBean")
public data class CustomFieldBean(
  public val description: String? = null,
  public val id: String? = null,
  public val isAllProjects: Boolean? = null,
  public val isLocked: Boolean? = null,
  public val isManaged: Boolean? = null,
  public val isTrusted: Boolean? = null,
  public val issueTypeIds: List<String>,
  public val issuesWithValue: Long? = null,
  public val lastValueUpdate: Instant? = null,
  public val name: String? = null,
  public val numericId: Long? = null,
  public val projectIds: List<Long>,
  public val projectsCount: Int? = null,
  public val screensCount: Int? = null,
  public val searcherKey: String? = null,
  public val self: String? = null,
  public val type: String? = null,
)
