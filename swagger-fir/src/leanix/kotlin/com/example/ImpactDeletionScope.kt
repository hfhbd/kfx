package com.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria to select impacts for deletion
 */
@Serializable
@SerialName(value = "ImpactDeletionScope")
public data class ImpactDeletionScope(
  /**
   * The fact sheet selection to which the impacts apply
   */
  public val scope: Scope? = null,
  /**
   * An EL expression to describe the advanced filter settings.
   */
  public val advanced: String? = null,
)
