package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria for selection of relations.
 */
@Serializable
@SerialName(value = "RelationDeletionScope")
public data class RelationDeletionScope(
  /**
   * A list of relation types like 'relToParent', 'relToChild'
   */
  public val relationTypes: List<String> = emptyList(),
  /**
   * The fact sheet selections where the relation scope is evaluated from
   */
  public val scope: Scope? = null,
  /**
   * An EL expression to describe the advanced filter settings.
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val advanced: String? = null,
  /**
   * Multiple components to be read and made available as part of the Juel evaluation context. They are referenced under the symbol 'lx'
   */
  public val read: ReadFactSheetSection? = null,
)
