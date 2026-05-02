package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class SecuritySchemeJsonBean(
  public val defaultSecurityLevelId: Long? = null,
  public val description: String? = null,
  public val id: Long? = null,
  public val levels: List<SecurityLevelJsonBean> = emptyList(),
  public val name: String? = null,
  public val self: String? = null,
)
