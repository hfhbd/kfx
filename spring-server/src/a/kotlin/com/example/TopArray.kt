package com.example

import kotlin.String
import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
public value class TopArray(
  public val `value`: List<String>,
)
