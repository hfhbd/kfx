package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A template representing a unique identifier of a Fact Sheet when evaluated
 */
@Serializable
@SerialName(value = "IdentifierTemplate")
public data class IdentifierTemplate(
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
)
