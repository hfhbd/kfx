package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "FieldConfigSchemeBean")
public data class FieldConfigSchemeBean(
  public val allIssueTypes: Boolean? = null,
  public val allProjects: Boolean? = null,
  public val defaultValue: Unit? = null,
  public val description: String? = null,
  public val `field`: FieldBean? = null,
  public val fieldConfigIds: List<Long>,
  public val id: Long? = null,
  public val issueTypes: List<IssueTypeJsonBean>,
  public val name: String? = null,
  public val projects: List<ProjectBean>,
  public val self: String? = null,
)
