package com.example

import kotlin.collections.List
import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
public value class TopIntEnumArray(
  public val `value`: List<TestEnumInt>,
)
