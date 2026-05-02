package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * The criteria to select To-dos for deletion
 */
@Serializable
public data class TodoDeletionScope(
  public val filterObject: TodoFilterObject? = null,
  /**
   * An EL expression to describe the advanced filter settings. Use 'lx.todo.*' as reference to To-do object obtained by filterObject
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val advanced: String? = null,
)
