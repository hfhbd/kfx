package com.example

import app.softwork.validation.MaxLength
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
  @MaxLength(inclusive = 5)
  public val s: String,
  public val arr: List<FooInputArr>,
)
