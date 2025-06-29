package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "IssueBean")
public data class IssueBean(
  public val changelog: ChangelogBean? = null,
  public val editmeta: EditMetaBean? = null,
  public val fields: Map<String, Fields>? = null,
  public val fieldsToInclude: IncludedFields? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val names: Map<String, String>? = null,
  public val operations: OpsbarBean? = null,
  public val properties: PropertiesBean? = null,
  public val renderedFields: Map<String, RenderedFields>? = null,
  public val schema: Map<String, JsonTypeBean>? = null,
  public val self: String? = null,
  public val transitionBeans: List<TransitionBean>,
  public val transitions: List<TransitionBean>,
  public val versionedRepresentations: Map<String, Map<String, VersionedRepresentations>>? = null,
)
