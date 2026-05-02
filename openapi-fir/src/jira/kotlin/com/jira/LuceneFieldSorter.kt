package com.jira

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

@Serializable
public data class LuceneFieldSorter(
  public val comparator: Unit? = null,
  public val documentConstant: String? = null,
)
