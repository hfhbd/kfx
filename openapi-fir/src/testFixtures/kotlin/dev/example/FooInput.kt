package dev.example

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Foo input sample
 */
@Serializable
@SerialName(value = "input")
public data class FooInput(
  public val input: String,
) : FooSealed
