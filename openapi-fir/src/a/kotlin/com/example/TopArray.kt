package com.example

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
public value class TopArray(
    public val value: List<String>,
)
