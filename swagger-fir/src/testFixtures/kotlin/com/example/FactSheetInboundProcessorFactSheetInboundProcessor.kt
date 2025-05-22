package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class FactSheetInboundProcessorFactSheetInboundProcessor(
  /**
   * An expression resolving to a Fact Sheet type present in the data model of your LeanIX workspace
   */
  public val type: String,
  /**
   * A template which is evaluated to represent a unique identifier of a Fact Sheet, or a group of Fact Sheets with certain criteria
   */
  public val identifier: IdentifierWithSearchScopeTemplate,
  /**
   * A list of changes that are performed to the specified fact sheet
   */
  public val updates: List<PatchTemplate>,
  /**
   * Multiple components to be read and made available as part of the Juel evaluation context. They are referenced under the symbol 'lx'
   */
  public val read: ReadFactSheetSection? = null,
)
