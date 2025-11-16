package com.example

import kotlin.Exception
import kotlin.Int
import kotlin.String
import kotlinx.serialization.Serializable

@Serializable
public data class Fault(
  public val httpReturnCode: Int,
  public val input: String? = null,
  public override val message: String,
  public val stackTrace: String? = null,
  public val statusCode: Int? = null,
) : Exception()
