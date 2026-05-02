package com.jira

import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class SecuritySchemesJsonBean(
  public val issueSecuritySchemes: List<SecuritySchemeJsonBean> = emptyList(),
)
