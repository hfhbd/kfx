package com.jira

import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueBean")
public data class IssueBean(
  public val changelog: ChangelogBean? = null,
  public val editmeta: EditMetaBean? = null,
  public val fields: Unit? = null,
  public val fieldsToInclude: IncludedFields? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val names: Unit? = null,
  public val operations: OpsbarBean? = null,
  public val properties: PropertiesBean? = null,
  public val renderedFields: Unit? = null,
  public val schema: Unit? = null,
  public val self: String? = null,
  public val transitionBeans: List<TransitionBean>,
  public val transitions: List<TransitionBean>,
  public val versionedRepresentations: Unit? = null,
)
