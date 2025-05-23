package com.jira

import kotlin.Int
import kotlin.String
import kotlin.collections.Map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "TransitionBean")
public data class TransitionBean(
  public val description: String? = null,
  public val fields: Map<String, FieldMetaBean>? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val opsbarSequence: Int? = null,
  public val to: StatusJsonBean? = null,
)
