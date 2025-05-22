package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Defines the factSheet field that controls the deletion, which will be done only if the value represents an empty list
 */
@Serializable
@SerialName(value = "FactSheetOwnerDeletion")
public data class FactSheetOwnerDeletion(
  /**
   * The name of the factSheet field that keep the list of owners for a factSheet
   */
  public val fieldName: String? = null,
  /**
   * The value that represents ownership for factSheets, if this value is found in fieldName, it will be removed
   */
  public val ownerId: String? = null,
)
