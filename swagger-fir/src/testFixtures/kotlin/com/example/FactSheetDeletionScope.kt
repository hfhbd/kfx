package com.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria to select fact sheets and activate the deletion.
 */
@Serializable
@SerialName(value = "FactSheetDeletionScope")
public data class FactSheetDeletionScope(
  /**
   * Optionally, defines  the owner field that controls deletion.
   */
  public val owner: FactSheetOwnerDeletion? = null,
  /**
   * The scope for fact sheets to be evaluated for deletion
   */
  public val scope: Scope? = null,
  /**
   * Multiple components to be read and made available as part of the Juel evaluation context. They are referenced under the symbol 'lx'
   */
  public val read: ReadFactSheetSection? = null,
)
