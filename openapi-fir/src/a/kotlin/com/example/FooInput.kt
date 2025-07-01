package com.example

import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.Serializable

/**
 * Foo input sample
 */
@Serializable
public data class FooInput(
  /**
   * asdf
   */
  public val s: String,
  public val arr: List<FooInputArr>,
)
