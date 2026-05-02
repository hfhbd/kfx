package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.Serializable

/**
 * A template representing the conditions for obtaining the fact sheets to been evaluated. Includes an identifier for internal, external Id, or a search criteria to obtain multiple fact sheets to evaluate
 */
@Serializable
public data class IdentifierWithSearchScopeTemplate(
  /**
   * An external identifier of the Fact Sheet that is likely an internal identifier of a foreign system
   */
  public val `external`: ExternalIdTemplate? = null,
  /**
   * An internal identifier of the Fact Sheet assigned by LeanIX
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val `internal`: String? = null,
  public val search: SearchScope? = null,
)
