package com.example

import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
public value class TopArrayInline(
  public val `value`: List<TopArrayInlineItems>,
)
