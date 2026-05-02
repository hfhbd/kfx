package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlin.Unit
import kotlinx.serialization.Serializable

/**
 * The criteria for selection of documents.
 */
@Serializable
public data class DocumentDeletionScope(
  /**
   * A list of matching patterns that document names have to match to be considered for deletion
   */
  public val documentMatches: Unit? = null,
  /**
   * The fact sheet selection where the document matches are evaluated from
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
