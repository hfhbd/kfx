package com.example

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable

@Serializable
public data class RelationInboundProcessorRelationInboundProcessor(
  /**
   * The relation type
   */
  public val type: String,
  /**
   * The source of the relation
   */
  public val from: IdentifierTemplate,
  /**
   * The target of the relation
   */
  public val to: IdentifierTemplate,
  /**
   * A list of changes that are performed to the specified relation
   */
  public val updates: List<PatchTemplate>,
  /**
   * Multiple components to be read for factsheet in from section and made available as part of the Juel evaluation context. They are referenced under the symbol 'lx'
   */
  public val read: List<ReadFactSheetSection>,
  /**
   * Make warnings just informative messages when any of the factsheets in the relation (from or to sides) does not exist. By default is False 
   */
  public val optional: Boolean? = null,
)
