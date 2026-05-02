package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The template that generates the fact sheet identifier using the ExternalID of the fact sheet
 */
@Serializable
@SerialName(value = "ExternalIdTemplate")
public data class ExternalIdTemplate(
  /**
   * The ExternalID of the fact sheet
   */
  public val id: KeyTemplate,
  /**
   * The connector type template
   */
  public val type: KeyTemplate,
)
