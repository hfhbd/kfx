package com.jira

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "AssociateProjectsBean")
public data class AssociateProjectsBean(
  public val idsOrKeys: List<String>,
)
