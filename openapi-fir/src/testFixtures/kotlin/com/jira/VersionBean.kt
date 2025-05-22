package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "VersionBean")
public data class VersionBean(
  public val archived: Boolean? = null,
  public val description: String? = null,
  public val expand: String? = null,
  public val id: String? = null,
  public val moveUnfixedIssuesTo: String? = null,
  public val name: String? = null,
  public val overdue: Boolean? = null,
  public val project: String? = null,
  public val projectId: Long? = null,
  public val releaseDate: Instant? = null,
  public val releaseDateSet: Boolean? = null,
  public val released: Boolean? = null,
  public val self: String? = null,
  public val startDate: Instant? = null,
  public val startDateSet: Boolean? = null,
  public val userReleaseDate: String? = null,
  public val userStartDate: String? = null,
)
