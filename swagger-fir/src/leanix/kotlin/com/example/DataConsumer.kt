package com.example

import kotlinx.serialization.Serializable

/**
 * Define the target location to which the outbound result LDIF should be uploaded to
 */
@Serializable
public sealed interface DataConsumer
