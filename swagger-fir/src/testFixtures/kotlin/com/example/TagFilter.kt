package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(value = "TagFilter")
public data class TagFilter(
  /**
   * The tag groups to retrieve
   */
  public val groups: List<String>,
  /**
   * Create list of tag group names by evaluating juel expression. It iterates all tagGroups in Workspace, tag group currently iterated over is available as “dm.tagGroup” in the JUEL expression, example: ${dm.tagGroup.name=='TagGroupsName'}
   */
  public val multipleGroups: List<String>,
)
