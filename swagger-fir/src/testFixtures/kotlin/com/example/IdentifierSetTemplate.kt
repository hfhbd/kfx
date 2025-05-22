package com.example

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
  public val `internal`: String? = null,
)
