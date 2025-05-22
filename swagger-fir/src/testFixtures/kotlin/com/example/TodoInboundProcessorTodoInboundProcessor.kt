package com.example

import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class TodoInboundProcessorTodoInboundProcessor(
  /**
   * A set of values that specify the ToDo payload request
   */
  public val updates: List<PatchTemplate>,
)
