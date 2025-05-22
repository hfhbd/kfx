package com.jira

import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SecuritySchemeJsonBean")
public data class SecuritySchemeJsonBean(
  public val defaultSecurityLevelId: Long? = null,
  public val description: String? = null,
  public val id: Long? = null,
  public val levels: List<SecurityLevelJsonBean>,
  public val name: String? = null,
  public val self: String? = null,
)
