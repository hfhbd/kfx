package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The criteria for selection of tags.
 */
@Serializable
@SerialName(value = "TagDeletionScope")
public data class TagDeletionScope(
  /**
   * A list of tag and tag group combinations.
   */
  public val tagScopes: List<TagScope>,
  /**
   * The fact sheet selection where the tag scopes are evaluated from
   */
  public val scope: Scope? = null,
  /**
   * An EL expression to describe the advanced filter settings.
   */
  public val advanced: String? = null,
  /**
   * Multiple components to be read and made available as part of the Juel evaluation context. They are referenced under the symbol 'lx'
   */
  public val read: ReadFactSheetSection? = null,
)
