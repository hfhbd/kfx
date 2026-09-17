package com.example

import io.github.hfhbd.validation.MaxLength
import io.github.hfhbd.validation.MinLength
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
  @MinLength(inclusive = 0)
  @MaxLength(inclusive = 500)
  public val advanced: String? = null,
)
