package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "LuceneFieldSorter")
public data class LuceneFieldSorter(
  public val comparator: Unit? = null,
  public val documentConstant: String? = null,
)
