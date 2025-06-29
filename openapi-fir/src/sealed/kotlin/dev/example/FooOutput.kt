package dev.example

import kotlin.Int
import kotlinx.serialization.Serializable

/**
 * Foo output sample
 */
@Serializable
public data class FooOutput(
  public val output: Int,
) : FooSealed
