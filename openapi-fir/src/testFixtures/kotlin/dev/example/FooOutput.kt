package dev.example

import kotlin.Int
import kotlinx.serialization.Serializable

/**
 * Foo input sample
 */
@Serializable
public data class FooOutput(
  public val input: Int,
) : FooSealed
