package com.example

import kotlin.Deprecated
import kotlin.String
import kotlin.collections.List
import kotlin.collections.emptyList
import kotlinx.serialization.Serializable

/**
 * Foo input sample
 */
@Serializable
public data class FooInput(
  /**
   * asdf
   */
  @Deprecated(message = "")
  public val s: String,
  public val `enum`: FooInputEnum? = null,
  public val arr: List<FooInputArr> = emptyList(),
)
