package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
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
  @MinLength(inclusive = 2)
  @MaxLength(inclusive = 5)
  public val s: String,
  public val arr: List<FooInputArr> = emptyList(),
)
