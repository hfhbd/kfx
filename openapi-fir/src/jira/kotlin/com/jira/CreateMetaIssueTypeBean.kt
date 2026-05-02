package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class CreateMetaIssueTypeBean(
  public val avatarId: Long? = null,
  public val description: String? = null,
  public val fields: Map<String, FieldMetaBean> = emptyMap(),
  public val iconUrl: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val subtask: Boolean? = null,
)
