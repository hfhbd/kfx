package com.example

import kotlin.Int
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The ratio limits for each type of deletion scope
 */
@Serializable
@SerialName(value = "MaximumDeletionRatio")
public data class MaximumDeletionRatio(
  /**
   * The maximum ratio for deletion in fact sheets
   */
  public val factSheets: Int? = null,
  /**
   * The maximum ratio for deletion in subscriptions
   */
  public val subscriptions: Int? = null,
  /**
   * The maximum ratio for deletion in documents
   */
  public val documents: Int? = null,
  /**
   * The maximum ratio for deletion in relations
   */
  public val relations: Int? = null,
  /**
   * The maximum ratio for deletion in tags
   */
  public val tags: Int? = null,
)
