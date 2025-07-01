package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "NavigableField")
public data class NavigableField(
  public val columnCssClass: String? = null,
  public val columnHeadingKey: String? = null,
  public val defaultSortOrder: String? = null,
  public val hiddenFieldId: String? = null,
  public val id: String? = null,
  public val name: String? = null,
  public val nameKey: String? = null,
  public val sortComparatorSource: FieldComparatorSource? = null,
  public val sorter: LuceneFieldSorter? = null,
)
