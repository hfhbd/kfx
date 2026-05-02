package com.example

import kotlin.String
import kotlinx.serialization.Serializable

/**
 * The template that generates one or more fact sheet identifiers using the ExternalID of the fact sheets
 */
@Serializable
public data class ExternalIdSetTemplate(
  /**
   * The ExternalIDs of the fact sheets
   */
  public val ids: String,
  /**
   * The connector type template
   */
  public val type: KeyTemplate,
)
