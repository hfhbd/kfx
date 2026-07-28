package com.example

import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class TopArrayInlineItems(
  public val byBar: List<TopArrayInlineItemsByBar>,
)
