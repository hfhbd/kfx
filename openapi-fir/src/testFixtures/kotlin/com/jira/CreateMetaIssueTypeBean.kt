package com.jira

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "CreateMetaIssueTypeBean")
public data class CreateMetaIssueTypeBean(
  public val avatarId: Long? = null,
  public val description: String? = null,
  public val fields: Map<String, FieldMetaBean>? = null,
  public val iconUrl: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val self: String? = null,
  public val subtask: Boolean? = null,
)
