package com.jira

import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
@SerialName(value = "IssueBean")
public data class IssueBean(
  public val changelog: ChangelogBean? = null,
  public val editmeta: EditMetaBean? = null,
  public val fields: JsonObject? = null,
  public val fieldsToInclude: IncludedFields? = null,
  public val id: String? = null,
  public val key: String? = null,
  public val names: Map<String, String> = emptyMap(),
  public val operations: OpsbarBean? = null,
  public val properties: PropertiesBean? = null,
  public val renderedFields: JsonObject? = null,
  public val schema: Map<String, JsonTypeBean> = emptyMap(),
  public val self: String? = null,
  public val transitionBeans: List<TransitionBean> = emptyList(),
  public val transitions: List<TransitionBean> = emptyList(),
  public val versionedRepresentations: JsonObject? = null,
)
