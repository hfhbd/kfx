package com.jira

import kotlinx.serialization.Serializable

@Serializable
public data class DefaultShareScopeBean(
  public val scope: DefaultShareScopeBeanScope? = null,
)
