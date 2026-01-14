package com.example

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
public value class TopIntEnumArray(
    public val value: List<TestEnumInt>,
)
