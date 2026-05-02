package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SecuritySchemesJsonBean")
public data class SecuritySchemesJsonBean(
  public val issueSecuritySchemes: List<SecuritySchemeJsonBean> = emptyList(),
)
