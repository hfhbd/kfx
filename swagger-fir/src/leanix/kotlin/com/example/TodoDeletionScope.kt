package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria to select To-dos for deletion
 */
@Serializable
@SerialName(value = "TodoDeletionScope")
public data class TodoDeletionScope(
  public val filterObject: TodoFilterObject? = null,
  /**
   * An EL expression to describe the advanced filter settings. Use 'lx.todo.*' as reference to To-do object obtained by filterObject
   */
  public val advanced: String? = null,
)
