package com.example

import kotlin.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria for selecting fact sheets and filter in combination with LDIF data
 */
@Serializable
@SerialName(value = "SearchScope")
public data class SearchScope(
  /**
   * The filter with conditions for obtaining fact sheets from your workspace
   */
  public val scope: Scope? = null,
  /**
   * The JUEL expression to filter results from search scope. References to LDIF fields are allowed here
   */
  public val filter: Unit? = null,
  /**
   * True is multiple matches are allowed for the target fact sheets to update. False if only one match is allowed
   */
  public val multipleMatchesAllowed: Unit? = null,
  /**
   * String or JUEL expression that define bookmark that wil be used for selecting factsheets.
   */
  public val scopeFromBookmark: Unit? = null,
  /**
   * The projection query request for obtaining projection PointOfViews, create this section as documented in projection endpoint (https://app.leanix.net/openapi-explorer/?urls.primaryName=Impacts). Every item from the PointOfViews response can be used in JUEL expressions by ${lx.projection}
   */
  public val projectionScope: Unit? = null,
)
