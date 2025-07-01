package com.jira

import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "SecurityListLevelJsonBean")
public data class SecurityListLevelJsonBean(
  public val levels: List<SecurityLevelJsonBean>,
)
