package dev.central

import kotlin.Double
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

@Serializable
public data class BrowseDeploymentsRequest(
  /**
   * The page number.
   */
  public val page: Double? = null,
  /**
   * The number of items per page.
   */
  public val size: Double? = null,
  /**
   * The field to sort the results by.
   */
  public val sortField: String,
  /**
   * The direction of the sorting (asc or desc).
   */
  public val sortDirection: String? = null,
  /**
   * Optional deployment IDs.
   */
  public val deploymentIds: List<String> = emptyList(),
  /**
   * Optional starting path.
   */
  public val pathStarting: String? = null,
)
