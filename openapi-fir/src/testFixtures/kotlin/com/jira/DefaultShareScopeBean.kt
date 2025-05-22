package com.jira

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DefaultShareScopeBean")
public data class DefaultShareScopeBean(
  public val scope: DefaultShareScopeBeanScope? = null,
)
