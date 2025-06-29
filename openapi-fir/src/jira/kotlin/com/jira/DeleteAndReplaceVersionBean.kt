package com.jira

import kotlin.Long
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "DeleteAndReplaceVersionBean")
public data class DeleteAndReplaceVersionBean(
  public val customFieldReplacementList: List<CustomFieldReplacement>,
  public val moveAffectedIssuesTo: Long? = null,
  public val moveFixIssuesTo: Long? = null,
)
