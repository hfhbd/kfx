package com.example

import kotlin.String
import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An object containing all necessary information about changes that are going to be made by the processors
 */
@Serializable
@SerialName(value = "Content")
public data class Content(
  /**
   * The type of content defined by this entry
   */
  public val type: String,
  /**
   * The (External) ID that identifies the fact sheet
   */
  public val id: String,
  /**
   * Processor specific fields that correspond to changes
   */
  public val `data`: Unit,
)
