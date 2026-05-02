package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.Map
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable

@Serializable
public data class TransitionBean(
  public val description: String? = null,
  public val fields: Map<String, FieldMetaBean> = emptyMap(),
  public val id: String? = null,
  public val name: String? = null,
  public val opsbarSequence: Int? = null,
  public val to: StatusJsonBean? = null,
)
