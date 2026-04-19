package com.example

import app.softwork.validation.MaxLength
import app.softwork.validation.MinLength
import kotlin.Deprecated
import kotlin.Int
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.emptyList
import kotlin.collections.emptyMap
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

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
  public val `enum`: FooInputEnum? = null,
  public val arr: List<FooInputArr> = emptyList(),
  public val unknown: JsonObject? = null,
  public val mapStringString: Map<String, String> = emptyMap(),
  public val mapStringInt: Map<String, Int> = emptyMap(),
  public val mapStringObject: Map<String, TestEnum> = emptyMap(),
  public val unknownObject: JsonObject? = null,
)
