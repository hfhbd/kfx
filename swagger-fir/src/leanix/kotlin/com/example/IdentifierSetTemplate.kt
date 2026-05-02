package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A template representing one or more identifiers of Fact Sheets
 */
@Serializable
@SerialName(value = "IdentifierSetTemplate")
public data class IdentifierSetTemplate(
  /**
   * External identifiers of the Fact Sheets
   */
  public val `external`: ExternalIdSetTemplate? = null,
  /**
   * Internal identifiers of the Fact Sheet assigned by LeanIX
   */
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val `internal`: String? = null,
)
